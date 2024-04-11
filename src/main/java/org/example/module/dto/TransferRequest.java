package org.example.module.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private Long sourceAccountId;
    private Long targetAccountId;
    private Long sourceWalletId;
    private Long targetWalletId;
    private BigDecimal amount;
    // other param...
}
