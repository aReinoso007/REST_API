package com.transaction.devsu.repository;

import com.transaction.devsu.entities.Transaction;
import com.transaction.devsu.entities.enums.TransactionTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findTransactionsByAccountAccountNumber(String accountNumber);
    Optional<List<Transaction>> findByAccountClientIdentificationAndTransactionDateBetween(String identification, Date initDate, Date endDate);
    Optional<List<Transaction>> findTransactionsByAccountClientIdentification(String identification);
    Optional<List<Transaction>> findTransactionsByAccountAccountNumberAndTransactionTypeAndTransactionDate(String accountNumber, TransactionTypeEnum transactionTypeEnum, Date date);

}
