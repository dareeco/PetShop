package com.example.petshop.web;

import com.example.petshop.service.TransactionLogService;
import com.example.petshop.web.dto.TransactionHistoryLogDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history-log")
@AllArgsConstructor
public class HistoryLogController {
    private final TransactionLogService transactionLogService;

    @GetMapping
    public List<TransactionHistoryLogDto> listAll() {
        return transactionLogService.listAll();
    }
}
