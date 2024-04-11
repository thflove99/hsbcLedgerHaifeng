package org.example.module.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author haifeng
 * @Date 2024-04-08
 *     STOCK,          // Stocks
 *     CURRENCY,       // Currencies
 *     CRYPTO_CURRENCY,// Cryptocurrencies
 *     BOND            // Bonds
 *     More asset types can be added as needed, and each asset type, along with a different currencyCode,
 *     represents a specific asset.
 *
 */
@Data
@Entity
@Table(name = "Asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "asset_id")
    private Long assetId;

    @Column(name = "asset_type", nullable = false)
    private String assetType;

    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
