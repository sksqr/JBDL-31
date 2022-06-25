package com.gfg.notificationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailSenderConfig {

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        //TODO: set emailId you want to use, do set app password on that (refer PPTs of L-20)
        javaMailSender.setUsername("jbdl.ewallet@gmail.com");
        //javaMailSender.setPassword("");
        //TODO: set password specific to you app
        javaMailSender.setPassword("apps_password");
        javaMailSender.setPort(587);
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.debug", true); // For debugging purpose
        return javaMailSender;
    }

}
