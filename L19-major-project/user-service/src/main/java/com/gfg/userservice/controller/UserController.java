package com.gfg.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gfg.userservice.request.UserCreationRequestDTO;
import com.gfg.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody UserCreationRequestDTO userCreationRequest) throws ExecutionException, JsonProcessingException, InterruptedException {
        Long userId = userService.createUser(userCreationRequest);
        return ResponseEntity.ok(userId);
    }


//TODO:    @GetMapping("/profile")

}
