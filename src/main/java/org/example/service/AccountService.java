package org.example.service;

import jakarta.transaction.Transactional;
import org.example.config.AccountStatus;
import org.example.repository.AccountRepository;
import org.example.module.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        // You can add some business logic here, such as generating an account ID
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setStatus(AccountStatus.OPEN.getStatus());
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    @Transactional
    public boolean updateAccountStatus(Long accountId, String status) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setStatus(status);
            accountRepository.save(account);
            return true;
        }
        return false;
    }
}
