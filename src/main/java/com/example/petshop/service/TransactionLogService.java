package com.example.petshop.service;

import com.example.petshop.model.TransactionHistoryLog;
import com.example.petshop.repository.TransactionHistoryLogRepository;
import com.example.petshop.web.dto.TransactionHistoryLogDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TransactionLogService {
    private final TransactionHistoryLogRepository transactionHistoryLogRepository;

    public List<TransactionHistoryLogDto> listAll() {

        List<TransactionHistoryLog> transactionHistoryLogs = transactionHistoryLogRepository.findAll();
        return transactionHistoryLogs.stream().map(desiredOutput ->
                new TransactionHistoryLogDto(desiredOutput.getDateOfPurchase(),desiredOutput.getSuccessfulTransactions(),desiredOutput.getUnsuccessfulTransactions())).toList();

    }
}
