package com.kafka.controller;

import com.kafka.model.User;
import com.kafka.producer.JsonKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {


    @Autowired
    JsonKafkaProducer jsonKafkaProducer;


    @PostMapping("/json/publish")
    public String publishMessage(@RequestBody User user){
        jsonKafkaProducer.sendJsonData(user);
        return "json data published to topic successfully";
    }
}
