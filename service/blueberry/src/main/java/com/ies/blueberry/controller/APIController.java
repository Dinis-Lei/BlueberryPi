package com.ies.blueberry.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ies.blueberry.exception.ResourceNotFoundException;
import com.ies.blueberry.model.NetHarvest;
import com.ies.blueberry.model.Alert;
import com.ies.blueberry.model.Location;
import com.ies.blueberry.model.PlantationTemperature;
import com.ies.blueberry.model.SoilPH;
import com.ies.blueberry.model.SoilWaterTension;
import com.ies.blueberry.model.StorageHumidity;
import com.ies.blueberry.model.StorageTemperature;
import com.ies.blueberry.model.UnitLoss;
import com.ies.blueberry.model.User;
import com.ies.blueberry.service.AllDataService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private AllDataService dataServ;

    @DeleteMapping("/deleteall")
    public void deleteAll() {
        dataServ.deleteAll();
    }

    @GetMapping("/locations")
    public List<Location> getLocations() {
        return dataServ.getLocations();
    }

    @PostMapping("/locations")
    public Location saveLocation(@Valid @RequestBody Location l) {
        return dataServ.saveLocation(l);
    }

    @GetMapping("/locations/{location}") 
    public ResponseEntity<Location> getLocationByName(@PathVariable(value = "location") String location)
        throws ResourceNotFoundException {
        Location l = dataServ.getLocationByName(location);
        if(l==null) {
            throw new ResourceNotFoundException("Location not found for this name :: " + location);
        }
        return ResponseEntity.ok().body(l);
    }

    @GetMapping("/{location}/{sensor}")
    public ResponseEntity<List<Optional<Object>>> getSensorByDateAndLocation(
        @PathVariable(value = "location") String location, @PathVariable(value = "sensor") String sensor, @RequestParam(required = false) String start, @RequestParam(required = false) String end
    ) {
        List<Optional<Object>> data;
        if(start==null && end==null) {
            data = dataServ.getData(location, sensor);
        }
        else {
            data = dataServ.getDataByDate(location, start, end, sensor);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{location}/{sensor}/{day}")
    public ResponseEntity<List<Optional<Object>>> getSensorByDayAndLocation(
        @PathVariable(value = "location") String location, @PathVariable(value = "sensor") String sensor,@PathVariable(value = "day") String date) 
    throws ResourceNotFoundException {
        List<Optional<Object>> data = dataServ.getDataByDay(location, date, sensor);
        if(data==null){
            throw new ResourceNotFoundException("Data not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{location}/{sensor}/{day}/{limit}")
    public ResponseEntity<List<Optional<Object>>> getSensorByDayAndLocationWithLimit(
        @PathVariable(value = "location") String location, @PathVariable(value = "sensor") String sensor,@PathVariable(value = "day") String date,@PathVariable(value = "limit") Integer limit) 
    throws ResourceNotFoundException {
        List<Optional<Object>> data = dataServ.getDataByDayWithLimit(location, date, sensor,limit);
        if(data==null){
            throw new ResourceNotFoundException("Data not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<Alert>> getAllAlerts(@RequestParam(required = false) Boolean seen,
    @RequestParam(required = false) String start, @RequestParam(required = false) String end) throws ResourceNotFoundException {
        List<Alert> alerts = dataServ.getAlerts(seen, start, end);
        if(alerts == null) { throw new ResourceNotFoundException("Alerts not found"); }
        return ResponseEntity.ok().body(alerts);
    }

    @PostMapping("/alerts")
    public Alert createAlert(@Valid @RequestBody Alert a) {
        return dataServ.saveAlert(a);
    }

    @PutMapping("/alerts")
    public void deleteAlert(@Valid @RequestBody Alert alert) throws ResourceNotFoundException {
        try {
            if (alert == null){
                throw new ResourceNotFoundException("Alert not found");
            }
            alert.setSeen(true);
            dataServ.saveAlert(alert);
        } 
        catch(NumberFormatException e) { System.out.println("Oh naur");}
    }

    @DeleteMapping("/alerts")
    public void clearAlerts() {
        dataServ.deleteAllAlerts();
    }

    @GetMapping("/{location}/{sensor}/alerts")
    public ResponseEntity<List<Alert>> getAlertByLocationAndSensor(
        @PathVariable(value = "location") String location, @PathVariable(value = "sensor") String sensor, 
        @RequestParam(required = false) Boolean seen, @RequestParam(required = false) String start, @RequestParam(required = false) String end) 
    throws ResourceNotFoundException {
        System.out.println("OI");
        List<Alert> alerts = dataServ.getAlertByLocationAndSensor(location, sensor, seen, start, end);
        if(alerts==null) {
            throw new ResourceNotFoundException("Location not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(alerts);
    }

    @PostMapping("/{location}/{sensor}/alert")
    public Alert createAlert(@Valid @RequestBody Alert a, @PathVariable(value = "location") String location, @PathVariable(value = "sensor") String sensor) {
        return dataServ.saveAlert(a);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() throws ResourceNotFoundException {
        List<User> users = dataServ.getUsers();
        if(users== null){
            throw new ResourceNotFoundException("No users found.");
        }
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User u) {
        return dataServ.saveUser(u);
    }   
    
    @GetMapping("/users/{user}")
    public ResponseEntity<User> getUserByName(@PathVariable(value="user") String username) throws ResourceNotFoundException {
        User user = dataServ.getUserByName(username);
        if(user==null){
            throw new ResourceNotFoundException("No user with username "+username);
        }
        return ResponseEntity.ok().body(user);
    }

    // Plantation Temperature

    // @GetMapping("/{location}/plantation_temperature") 
    // public ResponseEntity<List<PlantationTemperature>> getPlantationTemperatureByLocation(@PathVariable(value = "location") String location)
    //     throws ResourceNotFoundException {
    //     List<PlantationTemperature> plantationtemperature = dataServ.getPlantationTemperatureByLocation(location);
    //     System.out.println("AQUI");
    //     System.out.println(plantationtemperature);
    //     if(plantationtemperature==null)
    //     {
    //         throw new ResourceNotFoundException("Location not found for this id :: " + location);
    //     }
    //     return ResponseEntity.ok().body(plantationtemperature);
    // }

    @PostMapping("/{location}/plantation_temperature")
    public PlantationTemperature createPlantationTemperature(@Valid @RequestBody PlantationTemperature temp,@PathVariable(value = "location") String location) {
        return dataServ.savePlantationTemperature(temp,location);
    }

    // Net Harvest    

    // @GetMapping("/{location}/net_harvest")
    // public ResponseEntity<List<NetHarvest>> getNetHarvestByLocation(@PathVariable(value = "location") String location)
    //     throws ResourceNotFoundException {
    //     List<NetHarvest> netharvest = dataServ.getNetHarvestByLocation(location);
    //     if(netharvest==null)
    //     {
    //         throw new ResourceNotFoundException("Location not found for this id :: " + location);
    //     }
    //     return ResponseEntity.ok().body(netharvest);
    // }

    @PostMapping("/{location}/net_harvest")
    public NetHarvest createNetHarvest(@Valid @RequestBody NetHarvest netHarv,@PathVariable(value = "location") String location) {
        return dataServ.saveNetHarvest(netHarv,location);
    }

    // Soil pH
    // @GetMapping("/{location}/soil_ph")
    // public ResponseEntity<List<SoilPH>> getSoilPHByLocation(@PathVariable(value = "location") String location)
    //     throws ResourceNotFoundException {
    //     List<SoilPH> soilPH = dataServ.getSoilPHByLocation(location);
    //     if(soilPH==null)
    //     {
    //         throw new ResourceNotFoundException("Location not found for this id :: " + location);
    //     }
    //     return ResponseEntity.ok().body(soilPH);
    // }

    @PostMapping("/{location}/soil_ph")
    public SoilPH createSoilPH(@Valid @RequestBody SoilPH soilph,@PathVariable(value = "location") String location) {
        return dataServ.saveSoilPH(soilph,location);
    }

    // Soil Water Tension

    // @GetMapping("/{location}/soil_water_tension")
    // public ResponseEntity<List<SoilWaterTension>> getSoilWaterTensionByLocation(@PathVariable(value = "location") String location)
    //     throws ResourceNotFoundException {
    //     List<SoilWaterTension> soilwt = dataServ.getSoilWaterTensionByLocation(location);
    //     if(soilwt==null)
    //     {
    //         throw new ResourceNotFoundException("Location not found for this id :: " + location);
    //     }
    //     return ResponseEntity.ok().body(soilwt);
    // }

    @PostMapping("/{location}/soil_water_tension")
    public SoilWaterTension createSoilWaterTension(@Valid @RequestBody SoilWaterTension soilwt,@PathVariable(value = "location") String location) {
        return dataServ.saveSoilWaterTensions(soilwt,location);
    }

    // Unit Loss
    // @GetMapping("/{location}/unit_loss")
    // public ResponseEntity<List<UnitLoss>> getUnitLossByLocation(@PathVariable(value = "location") String location)
    //     throws ResourceNotFoundException {
    //     List<UnitLoss> unitloss = dataServ.getUnitLossByLocation(location);
    //     if(unitloss==null)
    //     {
    //         throw new ResourceNotFoundException("Location not found for this id :: " + location);
    //     }
    //     return ResponseEntity.ok().body(unitloss);
    // }

    @PostMapping("/{location}/unit_loss")
    public UnitLoss createUnitLoss(@Valid @RequestBody UnitLoss ul,@PathVariable(value = "location") String location) {
        return dataServ.saveUnitLoss(ul,location);
    }

    // Storage Temperature

    // @GetMapping("/{location}/storage_temperature")
    // public ResponseEntity<List<StorageTemperature>> getStorageTemperatureByLocation(@PathVariable(value = "location") String location)
    //     throws ResourceNotFoundException {
    //     List<StorageTemperature> storageTemperature = dataServ.getStorageTemperatureByLocation(location);
    //     if(storageTemperature==null)
    //     {
    //         throw new ResourceNotFoundException("Location not found for this id :: " + location);
    //     }
    //     return ResponseEntity.ok().body(storageTemperature);
    // }

    @PostMapping("/{location}/storage_temperature")
    public StorageTemperature createSTemperature(@Valid @RequestBody StorageTemperature st,@PathVariable(value = "location") String location) {
        return dataServ.saveStorageTemperature(st,location);
    }

    // Storage Humidity

    // @GetMapping("/{location}/storage_humidity")
    // public ResponseEntity<List<StorageHumidity>> getStorageHumidityByLocation(@PathVariable(value = "location") String location)
    //     throws ResourceNotFoundException {
    //     List<StorageHumidity> storageHumidity = dataServ.getStorageHumidityByLocation(location);
    //     if(storageHumidity==null)
    //     {
    //         throw new ResourceNotFoundException("Location not found for this id :: " + location);
    //     }
    //     return ResponseEntity.ok().body(storageHumidity);
    // }

    @PostMapping("/{location}/storage_humidity")
    public StorageHumidity createSHumidity(@Valid @RequestBody StorageHumidity sh,@PathVariable(value = "location") String location) {
        return dataServ.saveStorageHumidity(sh, location);
    }
}
