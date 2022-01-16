package com.ies.blueberry.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ies.blueberry.model.Location;
import com.ies.blueberry.model.NetHarvest;
import com.ies.blueberry.model.PlantationTemperature;
import com.ies.blueberry.model.SoilPH;
import com.ies.blueberry.model.SoilWaterTension;
import com.ies.blueberry.model.StorageHumidity;
import com.ies.blueberry.model.StorageTemperature;
import com.ies.blueberry.model.UnitLoss;
import com.ies.blueberry.service.AllDataService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private AllDataService dataServ;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String fileBody) {
        System.out.println("Message " + fileBody);
        Map<String,Object> result = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readValue(fileBody, HashMap.class);     
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(result);

        Double data = (Double) result.get("val");
        Long timestamp = Long.valueOf( (Integer) result.get("timestamp"));
        String location = (String) result.get("location");
        //System.out.println(data + " " + timestamp + " " + location);
        //System.out.println("1" + dataServ.getLocationByName(location));
        //System.out.println("AAAAAAAAAAAAAAAAa");
        if (dataServ.getLocationByName(location) == null){
            dataServ.saveLocation(new Location(location, 0L));
            //System.out.println("2" + dataServ.getLocationByName(location));
        }
        //Location l = dataServ.getLocationByName(location);

        switch((String) result.get("key")){
            case "plantation_temp":
                //System.out.println(result.get("val"));
                dataServ.savePlantationTemperature(new PlantationTemperature(data, location, timestamp), location);
                break;
            case "net_harvest":
                dataServ.saveNetHarvest(new NetHarvest(data, location, timestamp), location);
                break;
            case "ph":
                dataServ.saveSoilPH(new SoilPH(data, location, timestamp), location);
                break;
            case "water_tension":
                dataServ.saveSoilWaterTensions(new SoilWaterTension(data, location, timestamp), location);
                break;
            case "unit_loss":
                dataServ.saveUnitLoss(new UnitLoss(data, location, timestamp), location);
                break;
            case "store_temp":
                dataServ.saveStorageTemperature(new StorageTemperature(data, location, timestamp), location);
                break;
            case "store_humidity":    
                dataServ.saveStorageHumidity(new StorageHumidity(data, location, timestamp), location);
                break;
        }
        
    }
    
}
