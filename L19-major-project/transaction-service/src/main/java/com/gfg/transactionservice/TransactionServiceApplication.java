package com.gfg.transactionservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionServiceApplication {


    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

    public static void main(String[] args) {

        SpringApplication.run(TransactionServiceApplication.class,args);
    }
}