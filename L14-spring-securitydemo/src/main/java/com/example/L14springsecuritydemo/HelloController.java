package com.example.L14springsecuritydemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello!!");
    }
}
//E903A7ADE1C0B6C8803FF19EBF335FE0
//E903A7ADE1C0B6C8803FF19EBF335FE0
