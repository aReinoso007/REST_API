package com.transaction.devsu.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Max(value = 30, message = "Password must not be over 30 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}$",
    message = "Password must contain at least one digit, at least one lowercase letter and one uppercase, at least" +
            " one special character, no whitespace allowed and minimum length of 8 characters")
    private String password;
    @Column(name = "CLI_STATUS")
    private Boolean status;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Account> accounts;
}
