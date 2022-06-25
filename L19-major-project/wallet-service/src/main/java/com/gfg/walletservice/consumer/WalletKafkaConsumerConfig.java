package com.gfg.walletservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.walletservice.InsufficientBalanceException;
import com.gfg.walletservice.WalletService;
import com.gfg.walletservice.entity.Wallet;
import com.gfg.walletservice.repo.WalletRepo;
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
public class WalletKafkaConsumerConfig {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private WalletService walletService;

    private String txnTopic = "TRANSACTION_COMPLETED";

    private static Logger logger = LoggerFactory.getLogger(WalletKafkaConsumerConfig.class);

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
    public void listenTransactionInitTopic(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        logger.info("Consuming : {}",message);
        Map<String,Object> payload = objectMapper.readValue(message,Map.class);
        Long senderId = Long.valueOf((Integer) payload.get("fromUserId"));
        Long receiverId = Long.valueOf((Integer) payload.get("toUserId"));
        Double amount = (Double) payload.get("amount");
        Map<String,Object> map = new HashMap<>();
        map.put("transactionId",payload.get("id"));
            try {
                walletService.updateWalletForTransaction(senderId,receiverId,amount);
                map.put("status","SUCCESSFUL");
            } catch (ExecutionException | InterruptedException | InsufficientBalanceException e) {
                logger.error("Exception while updating wallet {}",e);
                map.put("status","FAILED");
            }

        String txnPayload = objectMapper.writeValueAsString(map);
        ListenableFuture<SendResult<String, String>> kafkaResponseFuture =  kafkaTemplate.send(txnTopic, String.valueOf(senderId),txnPayload);
        logger.info("Pushed to topic: {}, kafka resposne :{}",txnTopic, kafkaResponseFuture.get());

    }

}
