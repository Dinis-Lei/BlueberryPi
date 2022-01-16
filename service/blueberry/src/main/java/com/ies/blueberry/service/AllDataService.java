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
import org.springframework.beans.factory.support.ReplaceOverride;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.EntityManager;


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

    public Location getLocationByName(String name) {
        return repLocation.findLocationByName(name).orElse(null);
    }

    public void deleteAll() {
        repLocation.deleteAll();
    }

    // public List<List<Object>> getDataByDate(String name, String date) {
    //     LocalDate today = LocalDate.parse(date);
    //     LocalDate tomorrow = today.plusDays(1);
    //     ZoneId z = ZoneId.of("Europe/Lisbon");
    //     Long begin = today.atStartOfDay(z).toEpochSecond();
    //     Long end = tomorrow.atStartOfDay(z).toEpochSecond();
    //     return repLocation.findByTime(name, begin, end);
    // }

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
        return temp;
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
        return netHarv;
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
        return soilph;
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
        return ul;
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
        return st;
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
        return stHum;
    }
}

