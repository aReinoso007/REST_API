package com.transaction.devsu.service;

import com.transaction.devsu.dto.TransactionDTO;
import com.transaction.devsu.dto.mappers.TransactionMapper;
import com.transaction.devsu.entities.Account;
import com.transaction.devsu.entities.Transaction;
import com.transaction.devsu.repository.AccountRepository;
import com.transaction.devsu.repository.TransactionRepository;
import com.transaction.devsu.utils.CustomException;
import com.transaction.devsu.utils.messages.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              TransactionMapper transactionMapper,
                              AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountRepository= accountRepository;
    }

    public List<TransactionDTO> getAllTransactions(){
        try{
            Optional<List<Transaction>> transactions = Optional.of(transactionRepository.findAll());
            return transactionMapper.transactionListToTransactionDtoList(transactions.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Error getting transaction list at service "+e);
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }
    @Transactional
    public TransactionDTO makeTransaction(TransactionDTO transactionDTO, String numero){
        try{
           Account account = checkIfAccountExists(transactionDTO);
            return null;
        }catch (Exception e){
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    protected Account checkIfAccountExists(TransactionDTO transactionDTO){
        Optional<Account> account = accountRepository.findByAccountNumber(transactionDTO.getNumeroCuenta());
        if(account.isEmpty()) throw new CustomException(Response.RESOURCE_NOT_FOUND);
        return account.get();
    }

    protected Map<String, Object> processDebitTransaction(TransactionDTO transactionDTO, Account account){
        Map<String, Object> transactionResult = new HashMap<>();
        try{


            return transactionResult;
        }catch (Exception e){
            throw e;
        }
    }

}
