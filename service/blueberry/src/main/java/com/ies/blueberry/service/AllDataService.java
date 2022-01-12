package com.ies.blueberry.service;

import com.ies.blueberry.model.NetHarvest;
import com.ies.blueberry.model.Location;
import com.ies.blueberry.model.SoilPH;
import com.ies.blueberry.model.SoilWaterTension;
import com.ies.blueberry.model.StorageHumidity;
import com.ies.blueberry.model.StorageTemperature;
import com.ies.blueberry.model.UnitLoss;
import com.ies.blueberry.model.PlantationTemperature;
import com.ies.blueberry.repository.LocationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AllDataService {

    @Autowired
    private LocationRepository repLocation;

    public List<Location> getLocations() {
        return repLocation.findAll();
    }

    public Location saveLocation(Location l) {
        return repLocation.save(l);
    }

    //Temperature Section
    @Autowired
    public List<PlantationTemperature> getPlantationTemperatureByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getPlantationTemperatures();
    }

    public PlantationTemperature savePlantationTemperature(PlantationTemperature temp, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getPlantationTemperatures().add(temp);
        return temp;
    }

    // //Net Harvest Section

    // public List<NetHarvest> getNetHarvestByLocation(String location) {
    //     Location l = repLocation.findLocationByName(location).orElse(null);
    //     return l.getNetHarvests();
    // }

    // public NetHarvest saveNetHarvest(NetHarvest netHarv, String location) {
    //     Location l = repLocation.findLocationByName(location).orElse(null);
    //     l.getNetHarvests().add(netHarv);
    //     return netHarv;
    // }

    //Soil pH Section

    public List<SoilPH> getSoilPHByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getSoilPHs();
    }

    public SoilPH saveSoilPH(SoilPH soilph, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getSoilPHs().add(soilph);
        return soilph;
    }


    //Soil Water Tension Section
    public List<SoilWaterTension> getSoilWaterTensionByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getSoilWaterTensions();
    }

    public SoilWaterTension saveSoilWaterTensions(SoilWaterTension soilwt, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getSoilWaterTensions().add(soilwt);
        return soilwt;
    }

    //Unit Loss Section
    public List<UnitLoss> getUnitLossByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getUnitLosses();
    }

    public UnitLoss saveUnitLoss(UnitLoss ul, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getUnitLosses().add(ul);
        return ul;
    }

    //Storage Temperature
    public List<StorageTemperature> getStorageTemperatureByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getStorageTemperatures();
    }

    public StorageTemperature saveStorageTemperature(StorageTemperature st, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getStorageTemperatures().add(st);
        return st;
    }

    //Storage Humidity

    public List<StorageHumidity> getStorageHumidityByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getStorageHumidities();
    }

    public StorageHumidity saveStorageHumidity(StorageHumidity stHum, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getStorageHumidities().add(stHum);
        return stHum;
    }
}

