package com.transaction.devsu.dto.mappers;

import com.transaction.devsu.dto.AccountDTO;
import com.transaction.devsu.entities.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "accountNumber", target = "numeroCuenta")
    @Mapping(source = "accountType", target = "tipoCuenta")
    @Mapping(source = "initialBalance", target = "saldoInicial")
    @Mapping(source = "status", target = "estado")
    @Mapping(source = "client.identification", target = "clienteCedula")
    AccountDTO accontToAccountDTO(Account account);

    List<AccountDTO> accountListToAccountDTOList(List<Account> accounts);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    Account accountDTOToAccount(AccountDTO accountDTO);
}
