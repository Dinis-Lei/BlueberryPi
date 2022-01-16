package com.ies.blueberry.service;

import com.ies.blueberry.model.NetHarvest;
import com.ies.blueberry.model.Alert;
import com.ies.blueberry.model.Location;
import com.ies.blueberry.model.SoilPH;
import com.ies.blueberry.model.SoilWaterTension;
import com.ies.blueberry.model.StorageHumidity;
import com.ies.blueberry.model.StorageTemperature;
import com.ies.blueberry.model.UnitLoss;
import com.ies.blueberry.model.PlantationTemperature;
import com.ies.blueberry.repository.AlertRepository;
import com.ies.blueberry.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ReplaceOverride;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;


@Service
public class AllDataService {

    @Autowired
    private LocationRepository repLocation;

    @Autowired
    private AlertRepository repAlert;

    public List<Location> getLocations() {
        return repLocation.findAll();
    }

    public Location saveLocation(Location l) {
        return repLocation.save(l);
    }

    public Location getLocationByName(String name) {
        return repLocation.findLocationByName(name).orElse(null);
    }

    public List<Alert> getAlertByLocationAndSensor(String location, String sensor){
        return repAlert.findByLocationAndSensor(location, sensor);
    }

    //Temperature Section
    public List<PlantationTemperature> getPlantationTemperatureByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        System.out.println("AQUI2");
        System.out.println(l);
        return l.getPlantationTemperatures();
    }

    public PlantationTemperature savePlantationTemperature(PlantationTemperature temp, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setPlantationTemperature(temp);
        saveLocation(l);
        checkPlantationTemperatureAlert(l);
        return temp;
    }

    // If 10 minutes pass with critical temperature, create alert object
    public void checkPlantationTemperatureAlert(Location l){
        List<PlantationTemperature> data = l.getPlantationTemperatures();

        // check if there enough data for an alert
        if ( data.size() < 10){
            return;
        }

        List<PlantationTemperature> filtered_data = data.subList(data.size()-10, data.size());
        for(PlantationTemperature temp: filtered_data){
            if( temp.getData() < 21.5){
                return;
            }
        }

        List<Alert> alerts = repAlert.findByLocationAndSensor(l.getName(), "plantation_temperature");
        Alert alert = null;
        for(Alert a: alerts){
            if (a.getEnd() == filtered_data.get(filtered_data.size()-1).getTimestamp() - 60){
                alert = a;
                break;
            }
        }

        Double val = filtered_data.get(9).getData() - 21.5; 
        if(alert != null){
            alert.setEnd( filtered_data.get(9).getTimestamp());
            alert.setVal(val);
        }
        else{
            alert = new Alert(l.getName(), "plantation_temperature", filtered_data.get(0).getTimestamp(), filtered_data.get(9).getTimestamp(), val);
        }
        repAlert.save(alert);
    }

    public List<Alert> getPlantationTemperatureAlertByLocation(String location){
        return repAlert.findByLocationAndSensor(location, "plantation_temperature");
    }

    //Net Harvest Section

    public List<NetHarvest> getNetHarvestByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getNetHarvests();
    }

    public NetHarvest saveNetHarvest(NetHarvest netHarv, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setNetHarvest(netHarv);
        saveLocation(l);
        checkNetHarvestAlert(netHarv, l);
        return netHarv;
    }

    public void checkNetHarvestAlert(NetHarvest nh, Location location){
        if(nh.getData() < 4.5){
            Alert alert = null;
            List<Alert> alerts = repAlert.findByLocationAndSensor(location.getName(), "plantation_temperature");
            for(Alert a: alerts){
                if (a.getEnd() == nh.getTimestamp() - 24*60*60){
                    alert = a;
                    break;
                }
            }
            Double val = 4.5 - nh.getData(); 
            if( alert != null){
                alert.setEnd(nh.getTimestamp());
                alert.setVal(val);
            }
            else{
                alert = new Alert(location.getName(), "net_harvest", nh.getTimestamp(), nh.getTimestamp(), val);
            }
            repAlert.save(alert);
        }
    }

    public List<Alert> getNetHarvestAlertByLocation(String location){
        return repAlert.findByLocationAndSensor(location, "net_harvest");
    }

    //Soil pH Section

    public List<SoilPH> getSoilPHByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getSoilPHs();
    }

    public SoilPH saveSoilPH(SoilPH soilph, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setSoilPH(soilph);
        saveLocation(l);
        checkSoilPHAlert(l, soilph);
        return soilph;
    }

    public void checkSoilPHAlert(Location l, SoilPH ph){
        if (!(ph.getData() > 4 && ph.getData() < 6)) { 
            Double val;
            if(ph.getData()>4) val = 4 - ph.getData();
            else val = ph.getData() - 6;
            List<Alert> alerts = repAlert.findByLocationAndSensor(l.getName(), "soil_ph");
            for(Alert a : alerts) {
                if(a.getEnd() == ph.getTimestamp() - 24*60*60) {
                    a.setEnd(ph.getTimestamp());
                    a.setVal(val);
                    repAlert.save(a);
                    return;
                }
            }
            Alert alert = new Alert(l.getName(), "soil_ph", ph.getTimestamp(), ph.getTimestamp(),val);
            repAlert.save(alert);
        }
    }

    public List<Alert> getSoilPHAlertByLocation(String location){
        return repAlert.findByLocationAndSensor(location, "soil_ph");
    }


    //Soil Water Tension Section
    public List<SoilWaterTension> getSoilWaterTensionByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getSoilWaterTensions();
    }

    public SoilWaterTension saveSoilWaterTensions(SoilWaterTension soilwt, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setSoilWaterTension(soilwt);
        saveLocation(l);
        return soilwt;
    }

    //Unit Loss Section
    public List<UnitLoss> getUnitLossByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getUnitLosses();
    }

    public UnitLoss saveUnitLoss(UnitLoss ul, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setUnitLoss(ul);
        saveLocation(l);
        checkUnitLossAlert(l, ul);
        return ul;
    }

    public void checkUnitLossAlert(Location l, UnitLoss ul){
        if (ul.getData() >= 5) {
            Double val = ul.getData() - 5; 
            List<Alert> alerts = repAlert.findByLocationAndSensor(l.getName(), "unit_loss");
            for(Alert a : alerts) {
                if(a.getEnd() == ul.getTimestamp() - 7*24*60*60) {
                    a.setEnd(ul.getTimestamp());
                    a.setVal(val);
                    repAlert.save(a);
                    return;
                }
            }
            Alert alert = new Alert(l.getName(), "unit_loss", ul.getTimestamp(), ul.getTimestamp(),val);
            repAlert.save(alert);
        }
    }

    public List<Alert> getUnitLossAlertByLocation(String location){
        return repAlert.findByLocationAndSensor(location, "unit_loss");
    }

    //Storage Temperature
    public List<StorageTemperature> getStorageTemperatureByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getStorageTemperatures();
    }

    public StorageTemperature saveStorageTemperature(StorageTemperature st, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setStorageTemperature(st);
        saveLocation(l);
        checkStorageTempAlert(l, st);
        return st;
    }

    public void checkStorageTempAlert(Location l, StorageTemperature st){
        if (!(st.getData() < 3 && st.getData() > 0)) {
            Double val;
            if(st.getData() > 3) val = st.getData() - 3;
            else val = st.getData();
            List<Alert> alerts = repAlert.findByLocationAndSensor(l.getName(), "storage_temp");
            for(Alert a : alerts) {
                if(a.getEnd() == st.getTimestamp() - 60) {
                    a.setEnd(st.getTimestamp());
                    a.setVal(val);
                    repAlert.save(a);
                    return;
                }
            }
            Alert alert = new Alert(l.getName(), "storage_temp", st.getTimestamp(), st.getTimestamp(), val);
            repAlert.save(alert);
        }
    }

    public List<Alert> getStorageTempAlertByLocation(String location){
        return repAlert.findByLocationAndSensor(location, "storage_temp");
    }

    //Storage Humidity

    public List<StorageHumidity> getStorageHumidityByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getStorageHumidities();
    }

    public StorageHumidity saveStorageHumidity(StorageHumidity stHum, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setStorageHumidity(stHum);
        saveLocation(l);
        checkStorageHumidityAlert(l, stHum);
        return stHum;
    }

    public void checkStorageHumidityAlert(Location l, StorageHumidity sh){
        if (sh.getData() < 85) { 
            Double val = 85 - sh.getData();
            List<Alert> alerts = repAlert.findByLocationAndSensor(l.getName(), "storage_humidity");
            for(Alert a : alerts) {
                if(a.getEnd() == sh.getTimestamp() - 60) {
                    a.setEnd(sh.getTimestamp());
                    a.setVal(val);
                    repAlert.save(a);
                    return;
                }
            }
            Alert alert = new Alert(l.getName(), "storage_humidity", sh.getTimestamp(), sh.getTimestamp(),val);
            repAlert.save(alert);
        }
    }

    public List<Alert> getStorageHumidityAlertByLocation(String location){
        return repAlert.findByLocationAndSensor(location, "storage_humidity");
    }
}

