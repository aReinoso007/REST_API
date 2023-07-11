package com.transaction.devsu.client;

import com.transaction.devsu.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "DEV_CLIENTS")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Client extends Person {


    private String contrasena;
    private Boolean estado;



}
