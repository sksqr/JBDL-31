package com.gfg.notificationservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Map;

@EnableKafka
@Configuration
public class NotificationKafkaConsumerConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JavaMailSender javaMailSender;


    private static Logger logger = LoggerFactory.getLogger(NotificationKafkaConsumerConfig.class);

    @KafkaListener(topics = "USER_CREATED", groupId = "notification-email-service")
    public void sendWelcomeEmail(String message) throws JsonProcessingException {
        logger.info("Consuming : {}",message);
        Map<String,Object> payload = objectMapper.readValue(message,Map.class);
        logger.info("Data from USER_CREATED topic : {}",payload);

        String email = (String) payload.get("email");
        String name = (String) payload.get("name");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jbdl.ewallet@gmail.com");
        simpleMailMessage.setSubject("Welcome in JBDL-Wallet Family");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("Hello "+name+", Your account is ready.");
//        simpleMailMessage.setCc("<--cc:emailIds-->");

        javaMailSender.send(simpleMailMessage);

        logger.info("Email send to {}",email);
    }


    @KafkaListener(topics = "WALLET_UPDATED", groupId = "notification-email-service")
    public void sendWalletUpdateEmail(String message) throws JsonProcessingException {
        logger.info("Consuming : {}",message);
        Map<String,Object> payload = objectMapper.readValue(message,Map.class);
        logger.info("Data from WALLET_UPDATED topic : {}",payload);

        String email = (String) payload.get("email");
        Double balance = (Double) payload.get("balance");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jbdl.ewallet@gmail.com");
        simpleMailMessage.setSubject("Wallet Update!!");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("Your updated balance is Rs:"+balance);
//        simpleMailMessage.setCc("<--cc:emailIds-->");

        javaMailSender.send(simpleMailMessage);

        logger.info("Email send to {}",email);
    }

}
