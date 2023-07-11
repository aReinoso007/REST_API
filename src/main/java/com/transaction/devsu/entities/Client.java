package com.transaction.devsu.entities;

import com.transaction.devsu.entities.Cuenta;
import com.transaction.devsu.entities.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "DEV_CLIENTS")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Client extends Person {

    @Column(name="CLI_PASSWORD", nullable = false)
    @NotNull(message = "The password field is required")
    private String password;
    @Column(name = "CLI_STATUS")
    private Boolean status;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Cuenta> accounts;
}
