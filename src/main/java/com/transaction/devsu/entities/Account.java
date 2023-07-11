package com.transaction.devsu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DEV_ACCOUNTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Account {
    @Id
    @SequenceGenerator(
            name= "cuenta_sequence",
            sequenceName = "cuenta_sequencce",
            allocationSize=1
    )
    @Column(name = "ACC_ID", nullable = false)
    private Long id;
    @Column(name = "ACC_ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;
    @Column(name = "ACC_ACCOUNT_TYPE", nullable = false)
    private String accountType;
    @Column(name = "ACC_INITIAL_BALANCE", nullable = false)
    private Double initialBalance;
    @Column(name = "ACC_STATUS", nullable = false)
    private Boolean status;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Client.class)
    @JoinColumn(name = "ACT_CLI_ID", referencedColumnName = "PER_ID")
    private Client client;


}
