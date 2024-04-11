MERGE INTO Asset (asset_id, asset_type, currency_code, created_at, updated_at)
KEY (asset_id)
VALUES (100, 'Currency', 'USD', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO Asset (asset_id, asset_type, currency_code, created_at, updated_at)
KEY (asset_id)
VALUES (200, 'Currency', 'CNY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
