package com.transaction.devsu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transaction.devsu.entities.enums.TransactionTypeEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @NotNull(message = "Account Number is required")
    private String numeroCuenta;
    @NotNull(message = "Fecha Movimiento is required")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fechaMovimiento;
    private TransactionTypeEnum tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldoInicial;
    private String clienteCedula;


}
