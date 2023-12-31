package com.transaction.devsu.dto.mappers;

import com.transaction.devsu.dto.TransactionDTO;
import com.transaction.devsu.entities.Transaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(source = "account.accountNumber", target = "numeroCuenta")
    @Mapping(source = "transactionDate", target = "fechaMovimiento")
    @Mapping(source = "transactionType", target = "tipoMovimiento")
    @Mapping(source = "ammount", target = "valor")
    @Mapping(source = "initialBalance", target = "saldoInicial")
    @Mapping(source = "balanceAvailable", target = "saldoDisponible")
    @Mapping(source = "account.client.identification", target = "clienteCedula")
    @Mapping(source = "status", target = "estado")
    TransactionDTO transactionToTransactionDto(Transaction transaction);
    List<TransactionDTO> transactionListToTransactionDtoList(List<Transaction> transactions);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    Transaction transactionDTOToTransaction(TransactionDTO transactionDTO);
}
