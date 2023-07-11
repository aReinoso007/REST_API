package com.transaction.devsu.repository;

import com.transaction.devsu.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClienteByIdentificacion(String identificacion);
}
