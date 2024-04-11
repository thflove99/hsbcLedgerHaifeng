package org.example.module.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Transaction_hsbc")
public class Transaction {

    @Id
    private String id;

    @Column(name = "from_account_id", nullable = false)
    private Long fromAccountId;

    @Column(name = "from_wallet_id", nullable = false)
    private Long fromWalletId;

    @Column(name = "to_account_id", nullable = false)
    private Long toAccountId;

    @Column(name = "to_wallet_id", nullable = false)
    private Long toWalletId;

    @Column(name = "asset_id", nullable = false)
    private Long assetId;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false, length = 20)
    private String status;

}
