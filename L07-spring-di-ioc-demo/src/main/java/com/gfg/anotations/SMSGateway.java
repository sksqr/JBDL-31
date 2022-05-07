package com.gfg.anotations;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SMSGateway {

    public void sendSMS(String data){
        System.out.println("Sending SMS:"+data);
    }


    @PostConstruct
    public void initMethod(){
        System.out.println("initializing SMS Gateway");
    }
}
