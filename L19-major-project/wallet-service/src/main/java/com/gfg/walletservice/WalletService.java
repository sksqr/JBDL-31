package com.gfg.walletservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.walletservice.entity.Wallet;
import com.gfg.walletservice.repo.WalletRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class WalletService {

    private static Logger logger = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WalletRepo walletRepo;

    private String topic = "WALLET_UPDATED";

    @Transactional
    public void updateWalletForTransaction(Long senderId, Long receiverId, Double amount) throws ExecutionException, JsonProcessingException, InterruptedException, InsufficientBalanceException {
        Wallet senderWallet = walletRepo.findByUserId(senderId);
        Wallet receiverWallet = walletRepo.findByUserId(receiverId);

        if(senderWallet.getBalance() >= amount){
            senderWallet.setBalance(senderWallet.getBalance() - amount);
            receiverWallet.setBalance(receiverWallet.getBalance() + amount);
//            walletRepo.save(senderWallet);
//            walletRepo.save(receiverWallet);
            List<Wallet> walletList = new ArrayList<>();
            walletList.add(senderWallet);
            walletList.add(receiverWallet);
            walletRepo.saveAll(walletList);
            pushWalletUpdateEvent(senderWallet);
            pushWalletUpdateEvent(receiverWallet);
            return;
        }
        throw new InsufficientBalanceException();
    }


    public void pushWalletUpdateEvent(Wallet wallet) throws JsonProcessingException, ExecutionException, InterruptedException {
        String payload = objectMapper.writeValueAsString(wallet);
        ListenableFuture<SendResult<String, String>> kafkaResponseFuture =  kafkaTemplate.send(topic, String.valueOf(wallet.getUserId()),payload);
        logger.info("Pushed to topic: {}, kafka resposne :{}",topic, kafkaResponseFuture.get());
    }
}


