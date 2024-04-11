package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.config.AccountStatus;
import org.example.event.TransactionCreatedEvent;
import org.example.event.WalletBalanceChangeEvent;
import org.example.exception.InsufficientBalanceException;
import org.example.exception.WalletNotFoundException;
import org.example.message.TransactionsAsynMessageQueue;
import org.example.message.TransactionConverter;
import org.example.message.TransactionMessage;
import org.example.module.dto.TransactionHistoryRequest;
import org.example.module.entity.Wallet;
import org.example.module.dto.TransferRequest;
import org.example.module.entity.Transaction;
import org.example.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class TransactionService implements ITransactionService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private WalletService walletService;

    private AssetService assetService;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    private final TransactionsAsynMessageQueue transactionsAsynMessageQueue;

    @Autowired
    public TransactionService(@Lazy TransactionsAsynMessageQueue transactionsAsynMessageQueue) {
        this.transactionsAsynMessageQueue = transactionsAsynMessageQueue;
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Transaction transferFunds(TransferRequest transferRequest) {
        // Retrieve corresponding wallet information based on the wallet ID in the request
        Wallet sourceWallet = walletService.getWalletById(transferRequest.getSourceWalletId());
        Wallet targetWallet = walletService.getWalletById(transferRequest.getTargetWalletId());

        log.info("Checking asset types");
        checkAssetType(sourceWallet,targetWallet);

        log.info("Checking balance for wallet ID: {}", transferRequest.getSourceWalletId());
        // Check if the balance is sufficient
        checkBalance(sourceWallet, transferRequest.getAmount());

        BigDecimal fromBalance = sourceWallet.getBalance();
        if (fromBalance.compareTo(transferRequest.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in the wallet");
        }

        // Create a transaction record
        TransactionMessage transactionMessage = createTransactionMessage(transferRequest,sourceWallet,targetWallet);
        // Broadcast the transaction creation message to relevant clients
        eventPublisher.publishEvent(new TransactionCreatedEvent(transactionMessage,transactionMessage.getTransactionId()));
        // Send the transaction information to the message queue for asynchronous
        // processing and simulate separation of writing and querying
        transactionsAsynMessageQueue.sendMessage(transactionMessage);

        return TransactionConverter.convertTransactionMessageToTransaction(transactionMessage);
    }

    /**
     *   Ledger database will be very write heavy so design the database structure and usage
     *   keeping that in mind. Hints: “CQRS”.
     * @param transaction
     * @return
     */
    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionHistory(TransactionHistoryRequest request) {
        return transactionRepository.findByFromAccountIdAndFromWalletId(request.getFromAccountId(),
                request.getFromWalletId());
    }

    private void checkBalance(Wallet wallet, BigDecimal amount) {
        // Check if the wallet exists
        if (wallet == null) {
            throw new WalletNotFoundException("Wallet not found");
        }

        BigDecimal balance = wallet.getBalance();

        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in the wallet");
        }
    }

    private void checkAssetType(Wallet sourceWallet,Wallet targetWallet) {
        // Todo: Check asset type before transaction. For example, currency to stock cannot be transferred.
    }

    /**
     * All the requests to the ledger have to be executed as “all or nothing”，so use transactional for this
     * @param transaction
     */
    @Transactional
    public void processTransaction(Transaction transaction) {
        // Update the balance of the target wallet for the target account
        Wallet targetWallet = walletService.getWalletById(transaction.getToWalletId());
        BigDecimal targetBalance = targetWallet.getBalance().add(transaction.getAmount());
        targetWallet.setBalance(targetBalance);
        walletService.updateWallet(targetWallet);

        // Todo: Currency conversion
        // This is just an example; the method needs to be expanded in practice
        BigDecimal convertedAmount = currencyConversionService.convertCurrency(transaction.getAmount(),
                "CNY", "USD");

        // Update the balance of the source wallet for the source account
        Wallet sourceWallet = walletService.getWalletById(transaction.getFromWalletId());
        BigDecimal sourceBalance = sourceWallet.getBalance().subtract(transaction.getAmount());
        sourceWallet.setBalance(sourceBalance);
        walletService.updateWallet(sourceWallet);

        // Update the status of the transaction record to completed
        transaction.setStatus(AccountStatus.CLEARED.getStatus());
        transactionRepository.save(transaction);

        // Broadcast the balance change of the target wallet
        eventPublisher.publishEvent(new WalletBalanceChangeEvent(targetWallet, transaction.getToAccountId()));
        // Broadcast the balance change of the source wallet
        eventPublisher.publishEvent(new WalletBalanceChangeEvent(sourceWallet, transaction.getFromAccountId()));
    }


    private TransactionMessage createTransactionMessage(TransferRequest transferRequest,
                                                        Wallet sourceWallet,Wallet targetWallet) {
        // Create a transaction message object based on the transfer request
        String transactionId = UUID.randomUUID().toString();
        TransactionMessage transactionMessage = new TransactionMessage();
        transactionMessage.setTransactionId(transactionId);
        transactionMessage.setAmount(transferRequest.getAmount());
        transactionMessage.setSourceWalletId(transferRequest.getSourceWalletId());
        transactionMessage.setTargetWalletId(transferRequest.getTargetWalletId());
        transactionMessage.setAssetId(sourceWallet.getAssetId());
        transactionMessage.setFromAccountId(sourceWallet.getAccountId());
        transactionMessage.setToAccountId(targetWallet.getAccountId());
        transactionMessage.setStatus(AccountStatus.PENDING.getStatus());
        // Set other properties...
        return transactionMessage;
    }
}
