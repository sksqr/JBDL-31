package com.gfg.walletservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.walletservice.entity.Wallet;
import com.gfg.walletservice.repo.WalletRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WalletRepo walletRepo;

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @KafkaListener(topics = "USER_CREATED", groupId = "wallet-service")
    public void listenUserCreatedTopic(String message) throws JsonProcessingException {
        logger.info("Consuming : {}",message);
        Map<String,Object> payload = objectMapper.readValue(message,Map.class);
        Wallet wallet = Wallet.builder()
                .userId(Long.valueOf((Integer) payload.get("userId")))
                .email(String.valueOf(payload.get("email")))
                .balance(100.00).build();
        walletRepo.save(wallet);
    }

    @KafkaListener(topics = "TRANSACTION_INIT", groupId = "wallet-service")
    public void listenTransactionInitTopic(String message) throws JsonProcessingException {
        logger.info("Consuming : {}",message);
        Map<String,Object> payload = objectMapper.readValue(message,Map.class);

    }

}
