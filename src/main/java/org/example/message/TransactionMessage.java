package org.example.message;// TransactionMessage.java

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionMessage {
    private String transactionId;
    private BigDecimal amount;
    private Long fromAccountId;
    private Long toAccountId;
    private Long assetId;
    private Long sourceWalletId;
    private Long targetWalletId;
    private String status;

    @Override
    public String toString() {
        return "TransactionMessage{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", assetId=" + assetId +
                ", sourceWalletId=" + sourceWalletId +
                ", targetWalletId=" + targetWalletId +
                ", status='" + status + '\'' +
                '}';
    }
}
