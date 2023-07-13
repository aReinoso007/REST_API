package com.transaction.devsu.entities;

import com.transaction.devsu.entities.enums.GenderEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
    message = "Password must contain at least one digit, at least one lowercase letter and one uppercase, at least" +
            " one special character, no whitespace allowed and minimum length of 8 characters")
    private String password;
    @Column(name = "CLI_STATUS")
    private Boolean status;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Account> accounts;



}
