package com.ies.blueberry.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.blueberry.exception.ResourceNotFoundException;
import com.ies.blueberry.model.NetHarvest;
import com.ies.blueberry.model.PlantationTemperature;
import com.ies.blueberry.service.AllDataService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private AllDataService dataServ;

    //Plantation Temperature

    @GetMapping("/plantation_temperature") //All movies resources are fetched
    public List<PlantationTemperature> getPlantationTempData() {
        return dataServ.getPlantationTemperatures();
    }

    @GetMapping("/plantation_temperature/{location}") //One Movie resource is fetched
    public ResponseEntity<PlantationTemperature> getPlantationTemperatureByLocation(@PathVariable(value = "location") String location)
        throws ResourceNotFoundException {
        PlantationTemperature temperature = dataServ.getPlantationTemperatureByLocation(location);
        if(temperature==null)
        {
            throw new ResourceNotFoundException("Location not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(temperature);
    }

    @PostMapping("/plantation_temperature")
    public PlantationTemperature createTemperature(@Valid @RequestBody PlantationTemperature temp) {
        return dataServ.savePlantationTemperature(temp);
    }

    //Net Harvest 

    @GetMapping("/net_harvest") //All movies resources are fetched
    public List<NetHarvest> getNetHarvestData() {
        System.out.println("AQUI");
        return dataServ.getNetHarvest();
    }    

    @GetMapping("/net_harvest/{location}") //One Movie resource is fetched
    public ResponseEntity<NetHarvest> getNetHarvestByLocation(@PathVariable(value = "location") String location)
        throws ResourceNotFoundException {
        NetHarvest netharvest = dataServ.getNetHarvestByLocation(location);
        if(netharvest==null)
        {
            throw new ResourceNotFoundException("Net Harvest data not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(netharvest);
    }

    @PostMapping("/net_harvest")
    public NetHarvest createNetHarvest(@Valid @RequestBody NetHarvest netHarv) {
        System.out.println("RIGHT");
        return dataServ.saveNetHarvest(netHarv);
    }
}
