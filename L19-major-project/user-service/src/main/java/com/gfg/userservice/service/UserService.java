package com.gfg.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.userservice.UserRepo;
import com.gfg.userservice.entity.User;
import com.gfg.userservice.request.UserCreationRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String topic = "USER_CREATED";

    public Long createUser(UserCreationRequestDTO userCreationRequestDTO) throws JsonProcessingException, ExecutionException, InterruptedException {
        User user = User.builder().email(userCreationRequestDTO.getEmail())
                .phone(userCreationRequestDTO.getPhone())
                .name(userCreationRequestDTO.getName())
                .kycId(userCreationRequestDTO.getKycId())
                .build();
        userRepo.save(user);

        Map<String,Object> payload = new HashMap<>();
        payload.put("userId",user.getId());
        payload.put("email",user.getEmail());
        payload.put("name",user.getName());
        ListenableFuture<SendResult<String, String>> kafkaResponseFuture =  kafkaTemplate.send(topic, String.valueOf(user.getId()), objectMapper.writeValueAsString(payload));
        logger.info("Pushed in {}, Kafka Response: {}", topic, kafkaResponseFuture.get());

        return user.getId();
    }

}
