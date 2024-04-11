package org.example.service;

import org.example.module.dto.TransactionQuery;
import org.example.module.entity.Transaction;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TransactionQueryService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionHistory(TransactionQuery transactionQuery) {
        // Querying the transaction history of a specified account.
        return transactionRepository.findByFromAccountIdAndFromWalletIdAndCreatedAtBetween(
                transactionQuery.getFromAccountId(),transactionQuery.getFromWalletId(),
                transactionQuery.getStartTime(),transactionQuery.getEndTime());
    }
}
