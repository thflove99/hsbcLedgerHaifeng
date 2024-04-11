package org.example.module.dto;

import lombok.Data;

@Data
public class TransactionHistoryRequest {
    private Long fromAccountId;
    private Long fromWalletId;

    // Getters and setters
}
