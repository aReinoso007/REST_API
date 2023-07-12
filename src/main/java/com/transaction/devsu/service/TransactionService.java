package com.transaction.devsu.service;

import com.transaction.devsu.dto.TransactionDTO;
import com.transaction.devsu.dto.mappers.TransactionMapper;
import com.transaction.devsu.entities.Account;
import com.transaction.devsu.entities.Transaction;
import com.transaction.devsu.entities.enums.TransactionTypeEnum;
import com.transaction.devsu.repository.AccountRepository;
import com.transaction.devsu.repository.TransactionRepository;
import com.transaction.devsu.utils.CustomException;
import com.transaction.devsu.utils.Util;
import com.transaction.devsu.utils.messages.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
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

    public List<TransactionDTO> getTransactionsByAccountNumber(String accountNumber){
        try{
            Optional<List<Transaction>> transactionList = transactionRepository.findTransactionsByAccountAccountNumber(accountNumber);
            return transactionMapper.transactionListToTransactionDtoList(transactionList.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Error at getting transactions by account number {}",e);
            throw new CustomException(e.getMessage(), e.getCause());
        }
    }

    @Transactional
    public TransactionDTO makeTransaction(TransactionDTO transactionDTO){
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

    protected Map<String, Object> processtTransaction(TransactionDTO transactionDTO, Account account) throws ParseException {
        Map<String, Object> transactionResult = new HashMap<>();
        StringBuilder processMessage = new StringBuilder();
        BigDecimal saldoTransaccion = transactionDTO.getSaldoInicial().subtract(transactionDTO.getValor());
        Map<String, Object> resultCheck = checkTransactionWriteErrorMessage(transactionDTO);
        try{
               processMessage.append(checkTransactionWriteErrorMessage(transactionDTO));

            return transactionResult;
        }catch (Exception e){
            throw e;
        }
    }

    protected Map<String, Object> checkTransactionWriteErrorMessage(TransactionDTO transactionDTO) throws ParseException {
        StringBuilder processMessage = new StringBuilder();
        Map<String, Object> checkResult = new HashMap<>();
        Boolean transactionStatus = true;
        BigDecimal saldoTransaccion = transactionDTO.getSaldoInicial().subtract(transactionDTO.getValor());
        if(transactionDTO.getValor().compareTo(BigDecimal.ZERO) > 0 && transactionDTO.getTipoMovimiento().equals(TransactionTypeEnum.DEBIT)){
            processMessage.append(Response.INVALID_TRANSACTION_DEBIT);
            transactionStatus = false;
        }

        if(transactionDTO.getValor().compareTo(BigDecimal.ZERO) <=0 && transactionDTO.getTipoMovimiento().equals(TransactionTypeEnum.DEPOSIT)){
            processMessage.append(Response.INVALID_TRANSACTION_DEPOSIT);
            transactionStatus = false;
        }

        if(saldoTransaccion.compareTo(BigDecimal.ZERO) <= 0 && transactionDTO.getTipoMovimiento().equals(TransactionTypeEnum.DEBIT)){
            processMessage.append(Response.NO_FUNDS_AVAILABLE);
            transactionStatus = false;
        }
        /*True means it has exceeded, false it hasnt */
        if(checkDailyLimit(transactionDTO)) {
            processMessage.append(Response.DAILY_LIMIT_EXCEEDED);
            transactionStatus = false;
        }
        log.info("message "+processMessage.toString());
        log.info("status process "+transactionStatus);
        log.info("difference "+saldoTransaccion);
        processMessage.append(transactionStatus ? processMessage.append(Response.TRANSACTION_OK).toString() : processMessage.toString()) ;
        checkResult.put(Response.KEY_MESSAGE_TRANSACTION, processMessage);
        checkResult.put(Response.KEY_STATUS_TRANSACTION, transactionStatus);
        checkResult.put(Response.KEY_DIFFERENCE_TRANSACTION, saldoTransaccion);
        return checkResult;
    }

    protected TransactionDTO processDebit(TransactionDTO transactionDTO, Account account){
        try{
            BigDecimal newDifference = account.getInitialBalance().subtract(transactionDTO.getValor());
            if(newDifference.compareTo(BigDecimal.ZERO) <0) throw new CustomException(Response.NO_FUNDS_AVAILABLE);
            /*Transaction  transaction = Transaction.builder()
                    .initialBalance(account.getInitialBalance())
                    ;*/
            return null;
        }catch (Exception e){
            throw new CustomException(e.getMessage(), e.getCause());
        }

    }

    protected Boolean checkDailyLimit(TransactionDTO transactionDTO) throws ParseException {
        Boolean dailyLimitstatus = false;
        Optional<List<Transaction>> transactions = transactionRepository.
                findTransactionsByAccountAccountNumberAndTransactionTypeAndTransactionDate(transactionDTO.getClienteCedula(),
                        transactionDTO.getTipoMovimiento(),
                        Util.getTodaysDate());
        BigDecimal currentBalance = transactions
                .orElse(Collections.emptyList())
                .stream()
                .map(Transaction::getAmmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if(currentBalance.compareTo(Response.MAX_DAILY_DEBIT) > 0 || transactionDTO.getValor().compareTo(Response.MAX_DAILY_DEBIT) >0) dailyLimitstatus = true;
        return dailyLimitstatus;
    }

}
