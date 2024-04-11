package org.example.service;

import org.example.module.dto.TransactionHistoryRequest;
import org.example.module.dto.TransferRequest;
import org.example.module.entity.Transaction;

import java.util.List;

/**
 * @Author Fox
 * @Date 2024/4/8
 */
public interface ITransactionService {

    Transaction transferFunds(TransferRequest transferRequest);

    Transaction createTransaction(Transaction transaction);

    List<Transaction> getTransactionHistory(TransactionHistoryRequest request);

    void processTransaction(Transaction transaction);
}
