package com.transaction.devsu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "fecha", "cliente","numeroCuenta",
        "saldoInicial","estado","movimiento",
        "saldoDisponible"
})
public class TransactionReportDTO {
    @JsonProperty("Fecha")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String fecha;
    @JsonProperty("Cliente")
    private String cliente;
    @JsonProperty("numeroCuenta")
    private String numeroCuenta;
    @JsonProperty("SaldoInicial")
    private BigDecimal saldoInicial;
    @JsonProperty("Estado")
    private Boolean estado;
    @JsonProperty("Movimiento")
    private String movimiento;
    @JsonProperty("SaldoDisponible")
    private String saldoDisponible;

}
