package org.example.service;

import org.example.module.entity.Account;

/**
 * @Author Fox
 * @Date 2024/4/8
 */
public interface IAccountService {
    Account createAccount(Account account);
    Account getAccountById(Long accountId);
    boolean updateAccountStatus(Long accountId, String status);
}
