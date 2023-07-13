package com.transaction.devsu.dto.mappers;

import com.transaction.devsu.dto.TransactionReportDTO;
import com.transaction.devsu.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionReportMapper {

    @Mapping(source = "transactionDate", target = "fecha")
    @Mapping(source = "account.client.name", target = "cliente")
    @Mapping(source = "account.accountNumber", target = "numeroCuenta")
    @Mapping(source = "initialBalance", target = "saldoInicial")
    @Mapping(source = "status", target = "estado")
    @Mapping(source = "ammount", target = "movimiento")
    @Mapping(source = "balanceAvailable", target = "saldoDisponible")
    TransactionReportDTO getTransactionReportDTO(Transaction transaction);
    List<TransactionReportDTO> toReportDTOList(List<Transaction> transactions);

}
