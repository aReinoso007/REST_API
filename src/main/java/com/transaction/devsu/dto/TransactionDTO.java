package com.transaction.devsu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    @NotNull(message = "Account Number is required")
    private String numeroCuenta;
    @NotNull(message = "Fecha Movimiento is required")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaMovimiento;
    @NotNull(message = "Tipo Movimiento is required")
    private String tipoMovimiento;
    @NotNull(message = "Valor is required")
    private BigDecimal valor;
    @NotNull(message = "Saldo is required")
    private BigDecimal saldo;
    @NotNull(message = "Cliente cedula is required")
    private String clienteCedula;


}
