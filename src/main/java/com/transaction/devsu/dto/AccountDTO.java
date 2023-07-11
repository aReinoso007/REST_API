package com.transaction.devsu.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "The field numeroCuenta is required")
    private String numeroCuenta;
    @NotNull(message = "The field tipoCuenta is required")
    private String tipoCuenta;
    @NotNull(message = "The field saldoInicial is required")
    private BigDecimal saldoInicial;
    @NotNull(message = "The field estado is required")
    private Boolean estado;
    @NotNull(message = "The field clienteCedula is required")
    private String clienteCedula;

}
