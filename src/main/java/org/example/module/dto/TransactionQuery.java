package org.example.module.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionQuery {
    private Long fromAccountId;
    private Long fromWalletId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // Constructor, getters, and setters
}
