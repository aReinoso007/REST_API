package com.transaction.devsu.entities;

import com.transaction.devsu.entities.enums.GenderEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @SequenceGenerator(
            name= "client_sequence",
            sequenceName = "client_sequencce",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    @Column(name = "PER_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "PER_NAME",nullable = false)
    @NotNull(message = "Name field is required")
    @Pattern(regexp =  "^[a-zA-Z\\s]+$", message = "Name must only contain letters")
    private String name;

    @Column(name = "PER_GENDER")
    @NotNull(message = "Gender is required")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(name = "PER_AGE")
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "User must be 18 or older to open a bank account")
    @Max(value = 100, message = "User over the age of 100 must be accompanied by their grandparents to open a bank account")
    private Integer age;

    @Column(name = "PER_IDENTIFICATION")
    @Size(min = 10, max = 10, message = "ID must only contain 10 digits")
    @NotNull(message = "An ID number is required")
    private String identification;

    @Column(name = "PER_ADDRESS")
    @NotNull(message = "Address is required")
    private String address;

    @Column(name = "PER_PHONE_NUMBER")
    @NotNull(message = "Phone Number is required")
    private String phoneNumber;

}
