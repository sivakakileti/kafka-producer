package com.kafka.producer;

import com.kafka.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class JsonKafkaProducer {

    @Autowired
    KafkaTemplate<String, Object> jsonKafkaTemplate;

    /*
    This method in publishing data/message to kafka topic  "json-topic-message"
    this returns CompletableFuture object
    if we need to perform once the publishing is done, we can use methods like whenComplete, join, get
     */
    public void sendJsonData(User data){
        log.info("sending json data as "+data.toString());
        CompletableFuture<SendResult<String, Object>> future = jsonKafkaTemplate.send("json-topic-message", data);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + data.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        data.toString() + "] due to : " + ex.getMessage());
            }
        });

    }

}
