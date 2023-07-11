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
    @Mapping(source = "balance", target = "saldo")
    @Mapping(source = "account.client.identification", target = "clienteCedula")
    TransactionDTO toTransactionDto(Transaction transaction);
    List<TransactionDTO> toTransactionDtoList(List<Transaction> transactions);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", ignore = true)
    Transaction toTransaction(TransactionDTO transactionDTO);
}
