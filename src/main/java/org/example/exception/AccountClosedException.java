package org.example.exception;

public class AccountClosedException extends RuntimeException {

    public AccountClosedException(String message) {
        super(message);
    }

    public AccountClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
