package com.java_reflection;

import com.java_reflection.dao.TransactionHistory;
import com.java_reflection.service.HibernateMini;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ORMFramework {

    public static void main(String[] args) {
        SpringApplication.run(ORMFramework.class, args);
    }

    /**
     * initialize dummy transaction list object
     */
    private List<TransactionHistory> initDummyTransactions() {
        return Stream.of(
                new TransactionHistory(1551, "Kasuni", "Credit", 10000),
                new TransactionHistory(1364, "Achini", "Credit", 40000),
                new TransactionHistory(1673, "Sonali", "Debit", 340000),
                new TransactionHistory(1938, "Dulmi", "Debit", 210000)
        ).collect(Collectors.toList());
    }

    /**
     * method for get sql connection
     */
    private HibernateMini<TransactionHistory> getConnection() {
        try {
            return HibernateMini.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}