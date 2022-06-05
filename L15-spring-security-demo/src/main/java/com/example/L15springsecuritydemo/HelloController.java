package com.example.L15springsecuritydemo;

import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/user/hello")
    ResponseEntity<String> getHello(){
        //  UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("Hello User! "+ MDC.get("username"));
    }


    @GetMapping("/admin/hello")
    ResponseEntity<String> getHelloAdmin(){
        //  UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("Hello Admin! "+ MDC.get("username"));
    }


}
//E903A7ADE1C0B6C8803FF19EBF335FE0
//E903A7ADE1C0B6C8803FF19EBF335FE0
