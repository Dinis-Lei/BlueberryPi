package com.ies.blueberry.service;

import com.ies.blueberry.model.PlantationTemperature;
import com.ies.blueberry.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
public class AllDataService {
    
    @Autowired
    private TemperatureRepository tempRep;

    //Temperature Section

    public PlantationTemperature saveTemperature(PlantationTemperature temp) {
        return tempRep.save(temp);
    }

    public List<PlantationTemperature> getTemperatures() { 
        return tempRep.findAll();
    }

    public PlantationTemperature getTemperatureByLocation(String location) {
        return tempRep.findTemperatureByLocation(location).orElse(null);
    }
    
}

