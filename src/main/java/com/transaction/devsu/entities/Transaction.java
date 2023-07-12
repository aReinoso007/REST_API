package com.transaction.devsu.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transaction.devsu.entities.enums.TransactionTypeEnum;
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
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequencce"
    )
    @Column(name = "TRA_ID", nullable = false, unique = true)
    private Long id;
    @Column(name = "TRA_DATE")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate transactionDate;
    @Column(name = "TRA_TRANSACTION_TYPE")
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum transactionType;
    @Column(name = "TRA_AMMOUNT")
    private BigDecimal ammount;
    @Column(name = "TRA_INITIAL_BALANCE")
    private BigDecimal initialBalance;
    @Column(name = "TRA_BALANCE_AVAILABLE")
    private BigDecimal balanceAvailable;
    @Column(name = "TRA_STATUS")
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "TRA_ACC_ID", referencedColumnName = "ACC_ID")
    private Account account;
}
