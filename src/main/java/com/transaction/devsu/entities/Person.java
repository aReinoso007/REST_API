package com.transaction.devsu.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "DEV_PERSONS")
public class Person {
    @Id
    @SequenceGenerator(
            name= "cliente_sequence",
            sequenceName = "cliente_sequencce",
            allocationSize=1
    )
    @Column(name = "PER_ID", unique = true, nullable = false)
    private Long id;
    @Column(name = "PER_NAME",nullable = false)
    @NotNull(message = "Name field is required")
    @Pattern(regexp =  "^[a-zA-Z\\s]+$", message = "Name must only contain letters")
    private String name;
    @NotNull(message = "Gender is required")
    private String gender;
    @NotNull(message = "Age is required")
    private Integer age;
    @Size(min = 10, max = 10, message = "ID must only contain 10 digits")
    @NotNull(message = "An ID number is required")
    private String identification;
    @NotNull(message = "Address is required")
    private String address;
    @NotNull(message = "Phone Number is required")
    private String phoneNumber;


}
