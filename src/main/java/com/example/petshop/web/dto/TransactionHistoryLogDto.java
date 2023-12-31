package com.example.petshop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionHistoryLogDto {
    LocalDate dateOfPurchase;
    Integer successfulTransactions;
    Integer unsuccessfulTransactions;
}
