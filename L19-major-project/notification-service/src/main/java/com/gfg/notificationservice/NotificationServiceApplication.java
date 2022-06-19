package com.gfg.notificationservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotificationServiceApplication {


    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class,args);
    }
}
