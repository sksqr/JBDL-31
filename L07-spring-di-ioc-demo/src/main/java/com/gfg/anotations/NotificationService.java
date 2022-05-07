package com.gfg.anotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NotificationService {

    @Autowired
    private SMSGateway smsGateway;

    public void sendNotification(String data){
        smsGateway.sendSMS(data);
    }

}
