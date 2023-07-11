package com.transaction.devsu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DEV_TRANSACTIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Transaction {
    @Id
    @SequenceGenerator(
            name= "transaction_sequence",
            sequenceName = "transaction_sequencce",
            allocationSize=1
    )
    @Column(name = "TRA_ID", nullable = false, unique = true)
    private Long id;
    @Column(name = "TRA_DATE")
    private LocalDate transactionDate;
    @Column(name = "TRA_TRANSACTION_TYPE")
    private String transactionType;
    @Column(name = "TRA_AMMOUNT")
    private BigDecimal ammount;
    @Column(name = "TRA_BALANCE")
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "TRA_ACC_ID", referencedColumnName = "ACC_ID")
    private Account account;
}
