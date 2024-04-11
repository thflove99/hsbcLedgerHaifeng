package org.example.config;

public enum AccountStatus {
    OPEN("OPEN"),
    CLOSED("CLOSED"),
    CLEARED("CLEARED"),
    PENDING("PENDING");

    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
