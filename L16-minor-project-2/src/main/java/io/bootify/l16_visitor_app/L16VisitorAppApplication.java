package io.bootify.l16_visitor_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class L16VisitorAppApplication {


    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Encoded value of 123:"+passwordEncoder.encode("123"));

        SpringApplication.run(L16VisitorAppApplication.class, args);

    }

}
