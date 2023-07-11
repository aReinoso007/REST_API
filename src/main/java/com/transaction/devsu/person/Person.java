package com.transaction.devsu.person;

import jakarta.persistence.*;
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
    private Long id;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;

    
}
