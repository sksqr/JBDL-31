package com.gfg.transactionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.transactionservice.entity.Transaction;
import com.gfg.transactionservice.enums.TransactionStatus;
import com.gfg.transactionservice.repo.TransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@EnableKafka
@Configuration
public class TransactionKafkaConsumerConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionRepo transactionRepo;

    private static Logger logger = LoggerFactory.getLogger(TransactionKafkaConsumerConfig.class);

    @KafkaListener(topics = "TRANSACTION_COMPLETED", groupId = "transaction-service")
    public void listenUserCreatedTopic(String message) throws JsonProcessingException {
        logger.info("Consuming : {}",message);
        Map<String,Object> payload = objectMapper.readValue(message,Map.class);
        Long txnId = Long.valueOf(String.valueOf(payload.get("transactionId")));
        String status = (String) payload.get("status");
        Transaction transaction = transactionRepo.findById(txnId).get();
        transaction.setStatus(TransactionStatus.valueOf(status));
        transactionRepo.save(transaction);
    }

}
