package com.java_reflection.dao;

import com.java_reflection.annotation.Column;
import com.java_reflection.annotation.PrimaryKey;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionHistory {
    @PrimaryKey
    private long transactionId;
    @Column
    private int accountNumber;
    @Column
    private String name;
    @Column
    private String transactionType;
    @Column
    private double amount;

    public TransactionHistory(int accountNumber, String name, String transactionType, double amount) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "transactionId=" + transactionId +
                ", accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
