package com.transaction.devsu.service;

import com.transaction.devsu.dto.TransactionDTO;
import com.transaction.devsu.dto.mappers.TransactionMapper;
import com.transaction.devsu.entities.Account;
import com.transaction.devsu.entities.Transaction;
import com.transaction.devsu.entities.enums.TransactionTypeEnum;
import com.transaction.devsu.repository.AccountRepository;
import com.transaction.devsu.repository.TransactionRepository;
import com.transaction.devsu.utils.CustomException;
import com.transaction.devsu.utils.messages.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
           Map<String, Object> processResult = processtTransaction(transactionDTO, account);
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

    protected Map<String, Object> processtTransaction(TransactionDTO transactionDTO, Account account){
        Map<String, Object> transactionResult = new HashMap<>();
        StringBuilder processMessage = new StringBuilder();
        try{
               processMessage.append(checkTransactionWriteErrorMessage(transactionDTO));

            return transactionResult;
        }catch (Exception e){
            throw e;
        }
    }

    protected String checkTransactionWriteErrorMessage(TransactionDTO transactionDTO){
        StringBuilder processMessage = new StringBuilder();
        Boolean transactionStatus = true;
        if(transactionDTO.getValor().compareTo(BigDecimal.ZERO) > 0 && transactionDTO.getTipoMovimiento().equals(TransactionTypeEnum.DEBIT)){
            processMessage.append(Response.INVALID_TRANSACTION_DEBIT);
            transactionStatus = false;
        }

        if(transactionDTO.getValor().compareTo(BigDecimal.ZERO) <=0 && transactionDTO.getTipoMovimiento().equals(TransactionTypeEnum.DEPOSIT)){
            processMessage.append(Response.INVALID_TRANSACTION_DEBIT);
            transactionStatus = false;
        }

        if(transactionDTO.getSaldoInicial().compareTo(BigDecimal.ZERO) == 0 && transactionDTO.getTipoMovimiento().equals(TransactionTypeEnum.DEBIT)){
            processMessage.append(Response.NO_FUNDS_AVAILABLE);
            transactionStatus = false;
        }

        if(!checkDailyLimit(transactionDTO)) {
            processMessage.append(Response.DAILY_LIMIT_EXCEEDED);
            transactionStatus = false;
        }

        return transactionStatus ? processMessage.append(Response.TRANSACTION_OK).toString() : processMessage.toString();
    }

    protected Boolean checkDailyLimit(TransactionDTO transactionDTO){
        Boolean dailyLimitstatus = false;
        return dailyLimitstatus;
    }

}
