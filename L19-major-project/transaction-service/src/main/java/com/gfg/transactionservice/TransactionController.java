package com.gfg.transactionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gfg.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Long> doTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) throws ExecutionException, JsonProcessingException, InterruptedException {
        Long transactionId = transactionService.doTransaction(transactionRequestDTO);
        return ResponseEntity.ok(transactionId);
    }
}
