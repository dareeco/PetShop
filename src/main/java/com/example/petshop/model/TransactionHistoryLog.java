package com.example.petshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionHistoryLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    LocalDate dateOfPurchase;

    @Column(nullable = false)
    Integer successfulTransactions;

    @Column(nullable = false)
    Integer unsuccessfulTransactions;

    public TransactionHistoryLog(LocalDate dateOfPurchase, Integer successfulTransactions, Integer unsuccessfulTransactions) {
        this.dateOfPurchase = dateOfPurchase;
        this.successfulTransactions = successfulTransactions;
        this.unsuccessfulTransactions = unsuccessfulTransactions;
    }
}
