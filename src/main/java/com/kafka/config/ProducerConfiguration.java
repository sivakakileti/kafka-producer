package com.kafka.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfiguration {

    /*
    Topic creation with partitions
     */
    @Bean
    public NewTopic createTopic(){
        return new NewTopic("json-topic-message", 3, (short) 1);
    }

    /*
    BOOTSTRAP_SERVERS_CONFIG = the url of kakfa sever to publish our message
    *** we publish data to kafka topic as key and value so we should mention
        which serilizer for key i.e StringSerializer.class here
        and which serializer for value i.e., JsonSerializer.class
     */
    @Bean
    public Map<String, Object> producerConfigDetails(){
        Map<String, Object> configProps =new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return  configProps;
    }

    /*
    creating KafkaProducerFactory here
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigDetails());
    }

    /*
    creating KafkaTemplate here
     */
    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

}
