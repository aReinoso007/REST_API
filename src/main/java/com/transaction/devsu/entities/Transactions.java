package com.transaction.devsu.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.time.LocalDate;

@Entity
public class Transactions {
    @Id
    @SequenceGenerator(
            name= "movimiento_sequence",
            sequenceName = "movimiento_sequencce",
            allocationSize=1
    )
    private Long id;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
}
