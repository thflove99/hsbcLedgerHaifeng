package org.example.event;

import lombok.extern.slf4j.Slf4j;
import org.example.module.entity.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Subscribe to Wallet Balance Change Events
 * The ledger should broadcast any balance change of any wallet for its client to listen to.
 */
@Slf4j
@Component
public class WalletBalanceChangeListener {
    @Autowired
    private AccountService accountService; // Inject the accountService

    @EventListener
    public void handleWalletBalanceChange(WalletBalanceChangeEvent event) {
        Long accountId = event.getAccountId();
        log.info("Account balance changed for accountId={}", accountId);
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            log.info("Account balance changed for user: {}", account.getAccountName());
            // Now you have the user details, you can send the information to the user
            // For example:
            // sendMessageToUser(user, "Your account balance changed. Details: " + event.getSource());
        } else {
            log.warn("User not found for accountId={}", accountId);
        }

        // Handle Changes in Account Balance, where users can retrieve their desired messages by accountId.
        log.info("your account balance changed,details {}", event.getSource());

        // Now you have the account details, you can send the information to the client
        // For example:
        // sendMessageToUser(user, "Your account balance changed. Details: " + event.getSource());
    }
}
