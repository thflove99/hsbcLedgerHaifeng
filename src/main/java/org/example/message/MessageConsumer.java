package org.example.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer implements CommandLineRunner {

    private final TransactionsAsynMessageQueue transactionsAsynMessageQueue;

    @Autowired
    public MessageConsumer(TransactionsAsynMessageQueue transactionsAsynMessageQueue) {
        this.transactionsAsynMessageQueue = transactionsAsynMessageQueue;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting message consumer thread...");
        transactionsAsynMessageQueue.startConsumerThread();
    }
}
