package com.transaction.devsu.dto;

import com.transaction.devsu.entities.enums.AccountTypeEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String numeroCuenta;
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private String clienteCedula;

}
