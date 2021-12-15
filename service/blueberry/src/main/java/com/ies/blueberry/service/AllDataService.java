package com.ies.blueberry.service;

import com.ies.blueberry.model.Temperature;
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

    public Temperature saveTemperature(Temperature temp) {
        return tempRep.save(temp);
    }

    public List<Temperature> getTemperatures() { 
        return tempRep.findAll();
    }

    public Temperature getTemperatureByLocation(String location) {
        return tempRep.findTemperatureByLocation(location).orElse(null);
    }
    
}

