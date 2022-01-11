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
import com.ies.blueberry.repository.NetHarvestRepository;
import com.ies.blueberry.repository.PlantationTemperatureRepository;
import com.ies.blueberry.repository.SoilPHRepository;
import com.ies.blueberry.repository.SoilWaterTensionRepository;
import com.ies.blueberry.repository.StorageHumidityRepository;
import com.ies.blueberry.repository.StorageTemperatureRepository;
import com.ies.blueberry.repository.UnitLossRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AllDataService {

    @Autowired
    private LocationRepository repVilaReal;
    @Autowired
    private LocationRepository repGuarda;
    @Autowired
    private LocationRepository repMinho;

    @Autowired
    private LocationRepository repLocation;

    public List<Location> getLocations() {
        return repLocation.findAll();
    }

    //Temperature Section

    public List<PlantationTemperature> getPlantationTemperatureByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getPlantationTemperatures();
    }

    public List<PlantationTemperature> savePlantationTemperature(PlantationTemperature temp, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getPlantationTemperatures().add(temp);
        return l.getPlantationTemperatures();
    }

    //Net Harvest Section

    public List<NetHarvest> getNetHarvestByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getNetHarvests();
    }

    public List<NetHarvest> saveNetHarvest(NetHarvest netHarv, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getNetHarvests().add(netHarv);
        return l.getNetHarvests();
    }

    //Soil pH Section

    public List<SoilPH> getSoilPHByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getSoilPHs();
    }

    public List<SoilPH> saveSoilPH(SoilPH soilph, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getSoilPHs().add(soilph);
        return l.getSoilPHs();
    }


    //Soil Water Tension Section
    public List<SoilWaterTension> getSoilWaterTensionByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getSoilWaterTensions();
    }

    public List<SoilWaterTension> saveSoilWaterTensions(SoilWaterTension soilwt, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getSoilWaterTensions().add(soilwt);
        return l.getSoilWaterTensions();
    }

    //Unit Loss Section
    public List<UnitLoss> getUnitLossByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getUnitLosses();
    }

    public List<UnitLoss> saveUnitLoss(UnitLoss ul, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getUnitLosses().add(ul);
        return l.getUnitLosses();
    }

    //Storage Temperature
    public List<StorageTemperature> getStorageTemperatureByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getStorageTemperatures();
    }

    public List<StorageTemperature> saveStorageTemperature(StorageTemperature st, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getStorageTemperatures().add(st);
        return l.getStorageTemperatures();
    }

    //Storage Humidity

    public List<StorageHumidity> getStorageHumidityByLocation(String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        return l.getStorageHumidities();
    }

    public List<StorageHumidity> saveStorageHumidity(StorageHumidity stHum, String location) {
        Location l = repLocation.findLocationByName(location).orElse(null);
        l.getStorageHumidities().add(stHum);
        return l.getStorageHumidities();
    }
}

