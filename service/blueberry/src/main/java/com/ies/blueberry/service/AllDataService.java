package com.ies.blueberry.service;

import com.ies.blueberry.model.NetHarvest;
import com.ies.blueberry.model.SoilPH;
import com.ies.blueberry.model.SoilWaterTension;
import com.ies.blueberry.model.StorageTemperature;
import com.ies.blueberry.model.UnitLoss;
import com.ies.blueberry.model.PlantationTemperature;
import com.ies.blueberry.repository.NetHarvestRepository;
import com.ies.blueberry.repository.PlantationTemperatureRepository;
import com.ies.blueberry.repository.SoilPHRepository;
import com.ies.blueberry.repository.SoilWaterTensionRepository;
import com.ies.blueberry.repository.StorageTemperatureRepository;
import com.ies.blueberry.repository.UnitLossRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AllDataService {
    
    @Autowired
    private PlantationTemperatureRepository tempRep;
    @Autowired
    private NetHarvestRepository netHarvRep;
    @Autowired
    private SoilPHRepository soilPHRep;
    @Autowired
    private SoilWaterTensionRepository soilWTRep;
    @Autowired
    private UnitLossRepository unitLRep;
    @Autowired
    private StorageTemperatureRepository STRep;

    //Temperature Section

    public PlantationTemperature savePlantationTemperature(PlantationTemperature temp) {
        return tempRep.save(temp);
    }

    public List<PlantationTemperature> getPlantationTemperatures() { 
        return tempRep.findAll();
    }

    public PlantationTemperature getPlantationTemperatureByLocation(String location) {
        return tempRep.findPlantationTemperatureByLocation(location).orElse(null);
    }

    //Net Harvest Section

    public NetHarvest saveNetHarvest(NetHarvest netHarv)
    {
        return netHarvRep.save(netHarv);
    }

    public List<NetHarvest> getNetHarvest() { 
        return netHarvRep.findAll();
    }

    public NetHarvest getNetHarvestByLocation(String location) {
        return netHarvRep.findNetHarvestByLocation(location).orElse(null);
    }

    //Soil pH Section

    public SoilPH saveSoilPH(SoilPH soilPH)
    {
        return soilPHRep.save(soilPH);
    }

    public List<SoilPH> getSoilPH() { 
        return soilPHRep.findAll();
    }

    public SoilPH getSoilPHByLocation(String location) {
        return soilPHRep.findSoilPHByLocation(location).orElse(null);
    }

    //Soil Water Tension Section
    public SoilWaterTension saveSoilWaterTension(SoilWaterTension watTension) {
        return soilWTRep.save(watTension);
    }

    public List<SoilWaterTension> getSoilWaterTensions() { 
        return soilWTRep.findAll();
    }

    public SoilWaterTension getSoilWaterTensionByLocation(String location) {
        return soilWTRep.findSoilWaterTensionByLocation(location).orElse(null);
    }

    //Soil Water Tension Section
    public UnitLoss saveUnitLoss(UnitLoss unLoss) {
        return unitLRep.save(unLoss);
    }

    public List<UnitLoss> getUnitLoss() { 
        return unitLRep.findAll();
    }

    public UnitLoss getUnitLossByLocation(String location) {
        return unitLRep.findUnitLossByLocation(location).orElse(null);
    }

    //Storage Temperature
    public StorageTemperature saveStorageTemperature(StorageTemperature StTemp) {
        return STRep.save(StTemp);
    }

    public List<StorageTemperature> getStorageTemperature() { 
        return STRep.findAll();
    }

    public StorageTemperature getStorageTemperatureByLocation(String location) {
        return STRep.findStorageTemperatureByLocation(location).orElse(null);
    }


    
}

