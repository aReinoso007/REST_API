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

    public Cliente() {
    }

    public Cliente(String nombre, String genero, Integer edad, String identificacion, String direccion, String telefono, String contrasena, Boolean estado) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
