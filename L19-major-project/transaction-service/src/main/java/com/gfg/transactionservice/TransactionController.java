package com.gfg.transactionservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gfg.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> doTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) throws ExecutionException, JsonProcessingException, InterruptedException {
        String transactionId = transactionService.doTransaction(transactionRequestDTO);
        return ResponseEntity.ok(transactionId);
    }

    @GetMapping("/status")
    public ResponseEntity<String> checkStatus(@RequestParam String txnId){
        return ResponseEntity.ok(transactionService.checkStatus(txnId));
    }
}
