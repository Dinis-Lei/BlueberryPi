package com.ies.blueberry.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ies.blueberry.exception.ResourceNotFoundException;
import com.ies.blueberry.model.Temperature;
import com.ies.blueberry.service.AllDataService;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private AllDataService dataServ;

    @GetMapping("/temperature") //All movies resources are fetched
    public List<Temperature> getTempData() {
        return dataServ.getTemperatures();
    }

    @GetMapping("/temperature/{location}") //One Movie resource is fetched
    public ResponseEntity<Temperature> getMovieByLocation(@PathVariable(value = "location") String location)
        throws ResourceNotFoundException {
        Temperature temperature = dataServ.getTemperatureByLocation(location);
        if(temperature==null)
        {
            throw new ResourceNotFoundException("Movie not found for this id :: " + location);
        }
        return ResponseEntity.ok().body(temperature);
    }


    @PostMapping("/temperature") //A new movie resource is created
    public Temperature createTemperature(@Valid @RequestBody Temperature temp) {
        return dataServ.saveTemperature(temp);
    }
}
