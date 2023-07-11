package com.transaction.devsu.persona;

import jakarta.persistence.*;

@MappedSuperclass
public class Persona {
    @Id
    private Long id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
