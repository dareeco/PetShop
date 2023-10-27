package com.example.petshop.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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
