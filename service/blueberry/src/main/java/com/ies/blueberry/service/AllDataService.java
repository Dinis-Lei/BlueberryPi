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
import com.ies.blueberry.repository.NetHarvestRepository;
import com.ies.blueberry.repository.PlantationTemperatureRepository;
import com.ies.blueberry.repository.SoilPHRepository;
import com.ies.blueberry.repository.SoilWaterTensionRepository;
import com.ies.blueberry.repository.StorageHumidityRepository;
import com.ies.blueberry.repository.StorageTemperatureRepository;
import com.ies.blueberry.repository.UnitLossRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;


@Service
public class AllDataService {

    @Autowired
    private LocationRepository repLocation;

    @Autowired
    private AlertRepository repAlert;

    @Autowired
    private NetHarvestRepository repNetHarvest;

    @Autowired
    private PlantationTemperatureRepository repPlantationTemperature;

    @Autowired
    private SoilPHRepository repSoilPH;

    @Autowired
    private SoilWaterTensionRepository repSoilWaterTension;

    @Autowired
    private StorageHumidityRepository repStorageHumidity;

    @Autowired
    private StorageTemperatureRepository repStorageTemp;

    @Autowired
    private UnitLossRepository repUnitLoss;


    public List<Location> getLocations() {
        return repLocation.findAll();
    }

    public Location saveLocation(Location l) {
        return repLocation.save(l);
    }

    public Location getLocationByName(String name) {
        Location l = repLocation.findLocationByName(name).orElse(null);
        return l;
    }

    public List<Alert> getAlertByLocationAndSensor(String location, String sensor, Boolean seen, String start, String end){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        Long begin;
        Long finish;
        if(start==null) {
            begin = Long.MIN_VALUE;
        }
        else {
            LocalDateTime s = LocalDateTime.parse(start, formatter);
            begin = s.toEpochSecond(ZoneOffset.UTC);
        }
        if(end==null) {
            finish = Long.MAX_VALUE;
        }
        else {
            LocalDateTime e = LocalDateTime.parse(end, formatter);
            finish = e.toEpochSecond(ZoneOffset.UTC);
        }
            
        System.out.println("!!!!!!!!!!!!!!!" + begin + " " + finish);
        if(seen == null) return repAlert.findByLocationAndSensorAndStartGreaterThanEqualAndEndLessThanEqual(location, sensor, begin, finish);
        else {
            return repAlert.findByLocationAndSensorAndSeenAndStartGreaterThanEqualAndEndLessThanEqual(location, sensor, seen, begin, finish);
        }
        
    }

    public Alert saveAlert(Alert a) {
        repAlert.save(a);
        return a;
    }

    public void deleteAlert(long id) {
        repAlert.deleteById(id);
    }

    public void deleteAllAlerts() {
        repAlert.deleteAll();
    }

    public List<Alert> getAlerts(Boolean seen, String start, String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        Long begin;
        Long finish;
        if(start==null) {
            begin = Long.MIN_VALUE;
        }
        else {
            LocalDateTime s = LocalDateTime.parse(start, formatter);
            begin = s.toEpochSecond(ZoneOffset.UTC);
        }
        if(end==null) {
            finish = Long.MAX_VALUE;
        }
        else {
            LocalDateTime e = LocalDateTime.parse(end, formatter);
            finish = e.toEpochSecond(ZoneOffset.UTC);
        }

        if(seen == null) return repAlert.findByStartGreaterThanEqualAndEndLessThanEqual(begin, finish);
        else return repAlert.findBySeenAndStartGreaterThanEqualAndEndLessThanEqual(seen, begin, finish);
    }

    public void deleteAll() {
        repLocation.deleteAll();
    }

    public List<Optional<Object>> getData(String l, String dataType) {
        List<Optional<Object>> results = new ArrayList<Optional<Object>>();
        switch(dataType){
            case "plantation_temperature":
                results = repPlantationTemperature.findByLocation(l);
                Collections.reverse(results);
                return results;
            case "net_harvest":
                results = repNetHarvest.findByLocation(l);
                Collections.reverse(results);
                return results;
            case "soil_ph":
                results = repSoilPH.findByLocation(l);
                Collections.reverse(results);
                return results;
            case "soil_water_tension":
                results = repSoilWaterTension.findByLocation(l);
                Collections.reverse(results);
                return results;
            case "unit_loss":
                results = repUnitLoss.findByLocation(l);
                Collections.reverse(results);
                return results;
            case "storage_temperature":
                results = repStorageTemp.findByLocation(l);
                Collections.reverse(results);
                return results;
            case "storage_humidity":    
                results = repStorageHumidity.findByLocation(l);
                Collections.reverse(results);
                return results;
        }
        return results;
    }

    public List<Optional<Object>> getDataByDate(String l, String lowerlim, String upperlim, String dataType) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        Long begin;
        Long end;
        if(lowerlim==null) {
            begin = Long.MIN_VALUE;
        }
        else {
            LocalDateTime s = LocalDateTime.parse(lowerlim, formatter);
            begin = s.toEpochSecond(ZoneOffset.UTC);
        }
        if(upperlim==null) {
            end = Long.MAX_VALUE;
        }
        else {
            LocalDateTime e = LocalDateTime.parse(upperlim, formatter);
            end = e.toEpochSecond(ZoneOffset.UTC);
        }
        List<Optional<Object>> results = new ArrayList<Optional<Object>>();
        switch(dataType){
            case "plantation_temperature":
                results = repPlantationTemperature.findByLocationAndTimestampBetween(l, begin, end);
                Collections.reverse(results);
                return results;
            case "net_harvest":
                results = repNetHarvest.findByLocationAndTimestampBetween(l, begin, end);
                Collections.reverse(results);
                return results;
            case "soil_ph":
                results = repSoilPH.findByLocationAndTimestampBetween(l, begin, end);
                Collections.reverse(results);
                return results;
            case "soil_water_tension":
                results = repSoilWaterTension.findByLocationAndTimestampBetween(l, begin, end);
                Collections.reverse(results);
                return results;
            case "unit_loss":
                results = repUnitLoss.findByLocationAndTimestampBetween(l, begin, end);
                Collections.reverse(results);
                return results;
            case "storage_temperature":
                results = repStorageTemp.findByLocationAndTimestampBetween(l, begin, end);
                Collections.reverse(results);
                return results;
            case "storage_humidity":    
                results = repStorageHumidity.findByLocationAndTimestampBetween(l, begin, end);
                Collections.reverse(results);
                return results;
        }
        return results;
    }

    public List<Optional<Object>> getDataByDateWithLimit(String l, String lowerlim, String upperlim, String dataType, Integer limit)
    {
        List<Optional<Object>> results = getDataByDate(l, lowerlim, upperlim, dataType);
        Collections.reverse(results);
        return results.stream().limit(limit).collect(Collectors.toList());
    }

    public List<Optional<Object>> getDataByDay(String name, String date, String dataType) {
        LocalDate today = LocalDate.parse(date);
        LocalDate tomorrow = today.plusDays(1);
        ZoneId z = ZoneId.of("Europe/Lisbon");
        Long begin = today.atStartOfDay(z).toEpochSecond();
        Long end = tomorrow.atStartOfDay(z).toEpochSecond();
        List<Optional<Object>> results = new ArrayList<Optional<Object>>();
        switch(dataType){
            case "plantation_temperature":
                results = repPlantationTemperature.findByLocationAndTimestampBetween(name, begin, end);
                Collections.reverse(results);
                return results;
            case "net_harvest":
                results = repNetHarvest.findByLocationAndTimestampBetween(name, begin, end);
                Collections.reverse(results);
                return results;
            case "soil_ph":
                results = repSoilPH.findByLocationAndTimestampBetween(name, begin, end);
                Collections.reverse(results);
                return results;
            case "soil_water_tension":
                results = repSoilWaterTension.findByLocationAndTimestampBetween(name, begin, end);
                Collections.reverse(results);
                return results;
            case "unit_loss":
                results = repUnitLoss.findByLocationAndTimestampBetween(name, begin, end);
                Collections.reverse(results);
                return results;
            case "storage_temperature":
                results = repStorageTemp.findByLocationAndTimestampBetween(name, begin, end);
                Collections.reverse(results);
                return results;
            case "storage_humidity":    
                results = repStorageHumidity.findByLocationAndTimestampBetween(name, begin, end);
                Collections.reverse(results);
                return results;
        }
        return results;
    }

    public List<Optional<Object>> getDataByDayWithLimit(String name, String date,String dataType, Integer limit)
    {
        List<Optional<Object>> results = getDataByDay(name, date, dataType);
        Collections.reverse(results);
        return results.stream().limit(limit).collect(Collectors.toList());
    }

    //Temperature Section
    public List<PlantationTemperature> getPlantationTemperatureByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        System.out.println("AQUI2");
        System.out.println(l);
        return l.getPlantationTemperatures();
    }

    @Transactional
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

    @Transactional
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

    @Transactional
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

    @Transactional
    public SoilWaterTension saveSoilWaterTensions(SoilWaterTension soilwt, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setSoilWaterTension(soilwt);
        saveLocation(l);
        checkSoilWaterTensions(soilwt, l);
        return soilwt;
    }

    public void checkSoilWaterTensions(SoilWaterTension soilwt, Location location){
        List<SoilWaterTension> data = location.getSoilWaterTensions();
        
        // check if there enough data for an alert
        if ( data.size() < 10){
            return;
        }

        List<SoilWaterTension> filtered_data = data.subList(data.size()-10, data.size());
        long unixTime = System.currentTimeMillis() / 1000L;
        for(SoilWaterTension wt: filtered_data){
            if( wt.getData() < 20){
                return;
            }
            else if(unixTime/3600 > 12){
                return;
            }
        }

        List<Alert> alerts = repAlert.findByLocationAndSensor(location.getName(), "water_tension");
        Alert a = alerts.get(alerts.size() - 1);
        Alert alert = null;

        if(a.getEnd() == soilwt.getTimestamp() - 60){
            alert = a;
        }
        else{
            alert = new Alert(location.getName(), "water_tension", filtered_data.get(0).getTimestamp(), filtered_data.get(9).getTimestamp(), null);
        }
        repAlert.save(alert);
    }

    //Unit Loss Section
    public List<UnitLoss> getUnitLossByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getUnitLosses();
    }

    @Transactional
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

    @Transactional
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
            List<Alert> alerts = repAlert.findByLocationAndSensor(l.getName(), "storage_temperature");
            for(Alert a : alerts) {
                if(a.getEnd() == st.getTimestamp() - 60) {
                    a.setEnd(st.getTimestamp());
                    a.setVal(val);
                    repAlert.save(a);
                    return;
                }
            }
            Alert alert = new Alert(l.getName(), "storage_temperature", st.getTimestamp(), st.getTimestamp(), val);
            repAlert.save(alert);
        }
    }

    public List<Alert> getStorageTempAlertByLocation(String location){
        return repAlert.findByLocationAndSensor(location, "storage_temperature");
    }

    //Storage Humidity

    public List<StorageHumidity> getStorageHumidityByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getStorageHumidities();
    }

    @Transactional
    public StorageHumidity saveStorageHumidity(StorageHumidity stHum, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.setStorageHumidity(stHum);  
        saveLocation(l);
        return stHum;    
    }

    public void checkStorageHumidityAlert(String l, StorageHumidity sh){
        System.out.println("AAAAAA");
        if (sh.getData() < 85) { 
            System.out.println(sh);
            Double val = 85 - sh.getData();
            List<Alert> alerts = repAlert.findByLocationAndSensor(l, "storage_humidity");
            for(Alert a : alerts) {
                if(a.getEnd() == sh.getTimestamp() - 60) {
                    a.setEnd(sh.getTimestamp());
                    a.setVal(val);
                    repAlert.save(a);
                    return;
                }
            }
            Alert alert = new Alert(l, "storage_humidity", sh.getTimestamp(), sh.getTimestamp(), val);
            repAlert.save(alert);
        }
    }

    public List<Alert> getStorageHumidityAlertByLocation(String location){
        return repAlert.findByLocationAndSensor(location, "storage_humidity");
    }

}

