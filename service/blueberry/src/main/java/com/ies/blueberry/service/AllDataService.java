package com.ies.blueberry.service;

import com.ies.blueberry.model.NetHarvest;
import com.ies.blueberry.model.SoilPH;
import com.ies.blueberry.model.SoilWaterTension;
import com.ies.blueberry.model.PlantationTemperature;
import com.ies.blueberry.repository.NetHarvestRepository;
import com.ies.blueberry.repository.PlantationTemperatureRepository;
import com.ies.blueberry.repository.SoilPHRepository;
import com.ies.blueberry.repository.SoilWaterTensionRepository;

import org.springframework.beans.factory.annotation.Autowired;
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


    
}

