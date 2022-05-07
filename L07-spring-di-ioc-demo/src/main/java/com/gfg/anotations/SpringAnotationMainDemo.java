package com.gfg.anotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringAnotationMainDemo {

    @Bean
    public SMSGateway smsGatewayBean(){
        return new SMSGateway();
    }

    @Scope(value = "prototype")
    @Bean("notificationService")
    public NotificationService notificationServiceBean(){
        return new NotificationService();
    }


    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAnotationMainDemo.class);
        NotificationService notificationService = (NotificationService) ctx.getBean("notificationService");
        notificationService.sendNotification("OTP is 1098");










//        NotificationService notificationService2 = (NotificationService) ctx.getBean("notificationService");
//        if(notificationService2 == notificationService){
//            System.out.println("equal");
//        }
//        else {
//            System.out.println("not equal");
//        }


    }
}
