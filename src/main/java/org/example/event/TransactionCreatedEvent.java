package org.example.event;

import lombok.Getter;
import org.example.message.TransactionMessage;
import org.springframework.context.ApplicationEvent;


/**
 *
 */
@Getter
public class TransactionCreatedEvent extends ApplicationEvent {

    // Getter and setter methods
    private final String transactionId;

    public TransactionCreatedEvent(TransactionMessage source, String transactionId) {
        super(source);
        this.transactionId = transactionId;
    }

}
