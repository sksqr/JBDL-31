package com.gfg.transactionservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.transactionservice.TransactionRequestDTO;
import com.gfg.transactionservice.entity.Transaction;
import com.gfg.transactionservice.enums.TransactionStatus;
import com.gfg.transactionservice.repo.TransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class TransactionService {

    private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private String transactionInitTopic = "TRANSACTION_INIT";

    public String doTransaction(TransactionRequestDTO transactionRequestDTO) throws JsonProcessingException, ExecutionException, InterruptedException {
        Transaction transaction = Transaction.builder()
                .fromUserId(transactionRequestDTO.getFromUserId())
                .toUserId(transactionRequestDTO.getToUserId())
                .amount(transactionRequestDTO.getAmount())
                .status(TransactionStatus.PENDING)
                .txnId(UUID.randomUUID().toString())
                .build();
        transactionRepo.save(transaction);
        String payload = objectMapper.writeValueAsString(transaction);
        ListenableFuture<SendResult<String, String>> kafkaResponseFuture =  kafkaTemplate.send(transactionInitTopic, String.valueOf(transaction.getFromUserId()),payload);

        logger.info("Pushed to topic: {}, kafka resposne :{}",transactionInitTopic, kafkaResponseFuture.get());
        return transaction.getTxnId();
    }


    public String checkStatus(String txnId){
        Transaction transaction = transactionRepo.findByTxnId(txnId);
        return transaction.getStatus().name();
    }
}
