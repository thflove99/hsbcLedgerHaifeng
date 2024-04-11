package org.example.service;

import org.example.module.entity.Wallet;

import java.util.List;

/**
 * @author thflo
 * @Date 2024/4/8
 */
public interface IWalletService {

     Wallet createWallet(Wallet wallet);

     Wallet getWalletById(Long walletId);

     Wallet updateWallet(Wallet wallet);

     List<Wallet> getWalletsByAccountId(Long accountId);
}
