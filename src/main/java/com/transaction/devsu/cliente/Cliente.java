package com.transaction.devsu.cliente;

import com.transaction.devsu.persona.Persona;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Cliente extends Persona {

    @Id
    @SequenceGenerator(
            name= "cliente_sequence",
            sequenceName = "cliente_sequencce",
            allocationSize=1
    )
    private Long clientId;
    private String contrasena;
    private Boolean estado;
}
