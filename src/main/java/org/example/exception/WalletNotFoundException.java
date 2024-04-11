package org.example.exception;

/**
 * @Author Fox
 * @Date 2024/4/8
 */
public class WalletNotFoundException extends RuntimeException {
    public WalletNotFoundException(String message) {
        super(message);
    }
}
