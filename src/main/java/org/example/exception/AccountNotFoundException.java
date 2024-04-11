package org.example.exception;

/**
 * @Author Fox
 * @Date 2024/4/10
 */
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
