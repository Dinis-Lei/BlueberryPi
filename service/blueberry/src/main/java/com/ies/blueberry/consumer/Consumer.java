package com.ies.blueberry.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ies.blueberry.model.PlantationTemperature;
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
        
        switch((String) result.get("key")){
            case "storage_temperature":
                System.out.println(result.get("temp"));
                Double data = (Double) result.get("temp");
                Long timestamp = Long.valueOf( (Integer) result.get("timestamp"));
                dataServ.saveTemperature(new PlantationTemperature(data, "location1", timestamp));
                break;
        }
    }
}
