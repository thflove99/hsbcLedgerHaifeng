package org.example.message;

import lombok.extern.slf4j.Slf4j;
import org.example.module.entity.Transaction;
import org.example.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * The client of the ledger should be able to make movement requests in asynchronous
 * mode.
 */
@Slf4j
@Component
public class TransactionsAsynMessageQueue {
    @Autowired
    TransactionService transactionService;

    // Simulate message queue, use Map to store messages
    private final Map<String, TransactionMessage> messageMap = new HashMap<>();
    // mock use message middle to save data in db,in prod env,we can use kafka or other,and can add retry mechanism
    public void sendMessage(TransactionMessage transactionMessage) {
        // Put the message into the Map
        messageMap.put(transactionMessage.getTransactionId(), transactionMessage);
        log.info("Sending message to queue: {}", transactionMessage);
    }

    // Start a thread to simulate message consumption
    public void startConsumerThread() {
        new Thread(() -> {
            while (true) {
                // Use iterator to traverse the Map
                Iterator<Map.Entry<String, TransactionMessage>> iterator = messageMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, TransactionMessage> entry = iterator.next();
                    TransactionMessage message = entry.getValue();
                    log.info("Consuming message: {}", message);
                    Transaction transaction = TransactionConverter.convertTransactionMessageToTransaction(message);
                    // Write message consumption logic here, such as processing transactions
                    transactionService.createTransaction(transaction);
                    transactionService.processTransaction(transaction);
                    // Safely remove elements using the iterator
                    iterator.remove();
                }
                // Simulate delay during consumption
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("Consumer thread interrupted", e);
                }
            }
        }).start();
    }
}
