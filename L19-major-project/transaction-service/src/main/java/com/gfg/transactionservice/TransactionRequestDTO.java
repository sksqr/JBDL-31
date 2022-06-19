package com.gfg.transactionservice;

import lombok.Data;

@Data
public class TransactionRequestDTO {
    Long fromUserId;
    Long toUserId;
    Double amount;
}
