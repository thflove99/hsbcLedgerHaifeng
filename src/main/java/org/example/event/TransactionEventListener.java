package org.example.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionEventListener {
    
    @EventListener
    public void handleTransactionCreatedEvent(TransactionCreatedEvent event) {
        // 处理交易创建事件
        String transactionId = event.getTransactionId();
        // 在此处进行相关处理，例如通知客户端或记录日志
        log.info("Transaction message: {}", event.getSource());
        log.info("Transaction created: {}", transactionId);
    }
}
