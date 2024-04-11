package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.config.AccountStatus;
import org.example.exception.AccountClosedException;
import org.example.exception.AccountNotFoundException;
import org.example.module.dto.TransactionHistoryRequest;
import org.example.module.dto.TransactionQuery;
import org.example.module.dto.TransferRequest;
import org.example.module.entity.Account;
import org.example.module.entity.Transaction;
import org.example.service.AccountService;
import org.example.service.TransactionQueryService;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Api(tags = "assets API Operations related to Transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionQueryService transactionQueryService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/transfer")
    @ApiOperation(value = "transfer one wallet to other", response = Transaction.class)
    public ResponseEntity<String> transferFunds(@RequestBody List<TransferRequest> transferRequests) {
        try {
            for (TransferRequest transferRequest : transferRequests) {
                // Perform account check before transferring funds
                checkAccount(transferRequest);
                // Execute the transfer operation
                transactionService.transferFunds(transferRequest);
            }
            return ResponseEntity.ok("Asset transfers accepted, please check later");
        } catch (AccountClosedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account is closed");
        }
    }

    // Method to check account status
    private void checkAccount(TransferRequest transferRequest) {
        Account account = accountService.getAccountById(transferRequest.getSourceAccountId());
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        if (account.getStatus().equals(AccountStatus.CLOSED.getStatus())) {
            throw new AccountClosedException("Account is closed");
        }
        // If the account status is not closed, it is in normal status, and the transfer operation can continue
    }

    @PostMapping("/history")
    @ApiOperation(value = "query transaction with account info", response = Transaction.class)
    public ResponseEntity<List<Transaction>> getTransactionHistory(@RequestBody TransactionHistoryRequest request) {
        List<Transaction> transactionHistory = transactionService.getTransactionHistory(request);
        return ResponseEntity.ok(transactionHistory);
    }

    @PostMapping("/history/between")
    @ApiOperation(value = "query transaction with time, here show the CQRS design", response = Transaction.class)
    public ResponseEntity<List<Transaction>> getTransactionHistory(@RequestBody TransactionQuery request) {
        List<Transaction> transactionHistory = transactionQueryService.getTransactionHistory(request);
        return ResponseEntity.ok(transactionHistory);
    }
}
