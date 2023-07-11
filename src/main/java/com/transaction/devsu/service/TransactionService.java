package com.transaction.devsu.service;

import com.transaction.devsu.dto.mappers.TransactionMapper;
import com.transaction.devsu.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper){
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }
}
