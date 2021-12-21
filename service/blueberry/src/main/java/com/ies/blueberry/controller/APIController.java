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
import com.ies.blueberry.model.SoilPH;
import com.ies.blueberry.model.SoilWaterTension;
import com.ies.blueberry.model.UnitLoss;
import com.ies.blueberry.service.AllDataService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private AllDataService dataServ;

    //Plantation Temperature

    @GetMapping("/plantation_temperature")
    public List<PlantationTemperature> getPlantationTempData() {
        return dataServ.getPlantationTemperatures();
    }

    @GetMapping("/plantation_temperature/{location}") 
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
    public PlantationTemperature createPlantationTemperature(@Valid @RequestBody PlantationTemperature temp) {
        return dataServ.savePlantationTemperature(temp);
    }

    //Net Harvest 

    @GetMapping("/net_harvest")
    public List<NetHarvest> getNetHarvestData() {
        return dataServ.getNetHarvest();
    }    

    @GetMapping("/net_harvest/{location}")
    public ResponseEntity<NetHarvest> getNetHarvestByLocation(@PathVariable(value = "location") String location)
        throws ResourceNotFoundException {
        NetHarvest netharvest = dataServ.getNetHarvestByLocation(location);
        if(netharvest==null)
        {
            throw new ResourceNotFoundException("Location not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(netharvest);
    }

    @PostMapping("/net_harvest")
    public NetHarvest createNetHarvest(@Valid @RequestBody NetHarvest netHarv) {
        return dataServ.saveNetHarvest(netHarv);
    }

    //Soil pH
    @GetMapping("/soil_ph")
    public List<SoilPH> getSoilPH() {
        return dataServ.getSoilPH();
    }    

    @GetMapping("/soil_ph/{location}")
    public ResponseEntity<SoilPH> getSoilPHByLocation(@PathVariable(value = "location") String location)
        throws ResourceNotFoundException {
        SoilPH soilph = dataServ.getSoilPHByLocation(location);
        if(soilph==null)
        {
            throw new ResourceNotFoundException("Location not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(soilph);
    }

    @PostMapping("/soil_ph")
    public SoilPH createSoilPH(@Valid @RequestBody SoilPH soilPH) {
        return dataServ.saveSoilPH(soilPH);
    }

    //Soil Water Tension

    @GetMapping("/soil_water_tension")
    public List<SoilWaterTension> getSoilWTData() {
        return dataServ.getSoilWaterTensions();
    }

    @GetMapping("/soil_water_tension/{location}") 
    public ResponseEntity<SoilWaterTension> getSoilWaterTensionByLocation(@PathVariable(value = "location") String location)
        throws ResourceNotFoundException {
        SoilWaterTension soilwt = dataServ.getSoilWaterTensionByLocation(location);
        if(soilwt==null)
        {
            throw new ResourceNotFoundException("Location not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(soilwt);
    }

    @PostMapping("/soil_water_tension")
    public SoilWaterTension createSoilWaterTension(@Valid @RequestBody SoilWaterTension swt) {
        return dataServ.saveSoilWaterTension(swt);
    }

    //Unit Loss
    @GetMapping("/unit_loss")
    public List<UnitLoss> getUnitLossData() {
        return dataServ.getUnitLoss();
    }

    @GetMapping("/unit_loss/{location}") 
    public ResponseEntity<UnitLoss> getUnitLossByLocation(@PathVariable(value = "location") String location)
        throws ResourceNotFoundException {
        UnitLoss uL = dataServ.getUnitLossByLocation(location);
        if(uL==null)
        {
            throw new ResourceNotFoundException("Location not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(uL);
    }

    @PostMapping("/unit_loss")
    public UnitLoss createUnitLoss(@Valid @RequestBody UnitLoss ul) {
        return dataServ.saveUnitLoss(ul);
    }

}
