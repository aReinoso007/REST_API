package com.transaction.devsu.service;

import com.transaction.devsu.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public ReportService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

}
