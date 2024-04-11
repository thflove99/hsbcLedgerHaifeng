package org.example.service;

import org.example.module.entity.Wallet;
import org.example.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService implements IWalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElse(null);
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        return walletRepository.save(wallet);
    }

    @Override
    public List<Wallet> getWalletsByAccountId(Long accountId) {
        return walletRepository.findAllByAccountId(accountId);
    }

    // Other methods for CRUD operations can be added here
}
