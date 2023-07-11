package com.transaction.devsu.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @Size(min = 10, max = 10, message = "Cedula must contain 10 digits only")
    private String identification;
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must only contain letters")
    private String name;
    private String gender;
    @Min(value = 18, message = "User must be 18 or older to open a bank account")
    @Max(value = 100, message = "User over the age of 100 must be accompanied by their grandparents to open a bank account")
    private Integer age;
    private String address;
    @Pattern(regexp = "[0-9]+", message = "Phone number must only contain 10 digits")
    private String phoneNumber;
    private String password;
    private Boolean status;
}
