package org.example.message;

import org.example.module.entity.Transaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransactionConverter {

    public static Transaction convertTransactionMessageToTransaction(TransactionMessage message) {
        Transaction transaction = new Transaction();
        transaction.setId(message.getTransactionId());
        transaction.setFromWalletId(message.getSourceWalletId());
        transaction.setToWalletId(message.getTargetWalletId());
        transaction.setAmount(message.getAmount());
        transaction.setAssetId(message.getAssetId());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setUpdatedAt(LocalDateTime.now());
        transaction.setFromAccountId(message.getFromAccountId());
        transaction.setToAccountId(message.getToAccountId());
        transaction.setStatus(message.getStatus());
        // set other param...
        return transaction;
    }
}
