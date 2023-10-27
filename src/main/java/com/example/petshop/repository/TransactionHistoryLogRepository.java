package com.example.petshop.repository;

import com.example.petshop.model.TransactionHistoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryLogRepository extends JpaRepository<TransactionHistoryLog, Long> {
}
