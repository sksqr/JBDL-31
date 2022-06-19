package com.gfg.walletservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalletServiceApplication {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }


    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class,args);
    }

}
