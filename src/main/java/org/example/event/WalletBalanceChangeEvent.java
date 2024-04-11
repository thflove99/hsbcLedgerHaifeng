package org.example.event;

import lombok.Getter;
import org.example.module.entity.Wallet;
import org.springframework.context.ApplicationEvent;



/**
 * Define Wallet Balance Change Event
 * The ledger should broadcast any balance change of any wallet for its client to listen to.
 */
@Getter
public class WalletBalanceChangeEvent extends ApplicationEvent {
    private final Long accountId;
    
    public WalletBalanceChangeEvent(Wallet source, Long accountId) {
        super(source);
        this.accountId = accountId;
    }

}
