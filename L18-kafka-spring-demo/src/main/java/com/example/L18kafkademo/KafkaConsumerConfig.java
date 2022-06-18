package com.example.L18kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @KafkaListener(topics = "demo-topic", groupId = "email")
    public void listenKafkaTopic(String message){
        logger.info("Consuming : {}",message);
    }
}
