CREATE TABLE IF NOT EXISTS Account (
    account_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    identity_id VARCHAR(20) NOT NULL COMMENT 'Unique identifier for the entity',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    status VARCHAR(20) NOT NULL COMMENT 'Transaction status, cannot be empty'
);



CREATE TABLE IF NOT EXISTS Asset (
    asset_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Asset ID',
    asset_type VARCHAR(50) NOT NULL COMMENT 'STOCK, CURRENCY, CRYPTO_CURRENCY, can add more asset types',
    currency_code VARCHAR(50) NOT NULL COMMENT 'Asset code, for currency, it is USD, CNY; for stocks, it is the specific stock code, e.g., TSM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
);

CREATE TABLE IF NOT EXISTS  Wallet (
    wallet_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_id INT,
    asset_id INT,
    balance DECIMAL(18, 2) COMMENT 'Balance',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time'
);

CREATE TABLE IF NOT EXISTS Transaction_hsbc (
    transaction_id VARCHAR(36) NOT NULL PRIMARY KEY COMMENT 'Transaction ID, UUID format, cannot be empty',
    from_account_id INT NOT NULL COMMENT 'Source account ID, cannot be empty',
    from_wallet_id INT NOT NULL COMMENT 'Source wallet ID, cannot be empty',
    to_account_id INT NOT NULL COMMENT 'Destination account ID, cannot be empty',
    to_wallet_id INT NOT NULL COMMENT 'Destination wallet ID, cannot be empty',
    asset_id INT NOT NULL COMMENT 'Asset ID, cannot be empty',
    amount DECIMAL(18, 2) NOT NULL COMMENT 'Transaction amount, cannot be empty',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    status VARCHAR(20) NOT NULL COMMENT 'Transaction status, cannot be empty'
);
