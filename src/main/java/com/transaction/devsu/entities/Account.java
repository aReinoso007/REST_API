package com.transaction.devsu.entities;

import com.transaction.devsu.entities.enums.AccountTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "DEV_ACCOUNTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Account {
    @Id
    @SequenceGenerator(
            name= "account_sequence",
            sequenceName = "account_sequencce",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    @Column(name = "ACC_ID", nullable = false)
    private Long id;
    @Column(name = "ACC_ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;
    @Column(name = "ACC_ACCOUNT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;
    @Column(name = "ACC_INITIAL_BALANCE", nullable = false)
    private BigDecimal initialBalance;
    @Column(name = "ACC_STATUS", nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
    @JoinColumn(name = "ACT_CLI_ID", referencedColumnName = "PER_ID")
    private Client client;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Transaction> transactions;


}
