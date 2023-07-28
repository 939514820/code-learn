package com.example.transaction.enums;

public enum TransactionIsolationLevel {
    TRANSACTION_READ_UNCOMMITTED(1),
    TRANSACTION_READ_COMMITTED(2),
    TRANSACTION_REPEATABLE_READ(3),
    TRANSACTION_SERIALIZABLE(4),
    NONE(5);
    private int level;

    TransactionIsolationLevel(int i) {
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
