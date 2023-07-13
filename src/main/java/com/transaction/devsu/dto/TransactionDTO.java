package com.transaction.devsu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transaction.devsu.entities.enums.TransactionTypeEnum;
import com.transaction.devsu.entities.enums.TransactionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private String numeroCuenta;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fechaMovimiento;
    private TransactionTypeEnum tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldoInicial;
    private BigDecimal saldoDisponible;
    private String clienteCedula;
    private Boolean estado;


}
