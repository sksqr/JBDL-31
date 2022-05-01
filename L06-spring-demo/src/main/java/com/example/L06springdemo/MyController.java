package com.example.L06springdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController("/")
public class MyController {

    @GetMapping
    public String hello(){
        return "Hello from thread:"+Thread.currentThread().getName();
    }

    @PostMapping
    public String helloPost(){
        return "Hello Post from thread:"+Thread.currentThread().getName();
    }


    @GetMapping("greeting")
    public String hello2(){
        return "Hi from thread:"+Thread.currentThread().getName();
    }
}
