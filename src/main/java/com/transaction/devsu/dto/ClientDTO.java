package com.transaction.devsu.dto;


import lombok.*;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDTO {

    private String cedula;
    private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private String numeroTelefono;
    private String contrasena;
    private Boolean estado;
}
