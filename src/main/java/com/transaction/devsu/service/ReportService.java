package com.transaction.devsu.service;

import com.transaction.devsu.dto.TransactionReportDTO;
import com.transaction.devsu.dto.mappers.TransactionReportMapper;
import com.transaction.devsu.entities.Transaction;
import com.transaction.devsu.repository.TransactionRepository;
import com.transaction.devsu.utils.CustomException;
import com.transaction.devsu.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReportService {

    private final TransactionRepository transactionRepository;
    private final TransactionReportMapper transactionReportMapper;

    @Autowired
    public ReportService(TransactionRepository transactionRepository, TransactionReportMapper transactionReportMapper){
        this.transactionRepository = transactionRepository;
        this.transactionReportMapper = transactionReportMapper;
    }

    public List<TransactionReportDTO> geTransactionReportByIdentificationAndDateRange(String identification, String initDate, String endDate){
        try{
            Optional<List<Transaction>> transactions = transactionRepository.
                    findByAccountClientIdentificationAndTransactionDateBetween(
                            identification, Util.formatDateInputs(initDate), Util.formatDateInputs(endDate));
            return transactionReportMapper.toReportDTOList(transactions.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("error getting report {}",e);
            throw new CustomException(e.getMessage(), e.getCause());
        }


    }

}
