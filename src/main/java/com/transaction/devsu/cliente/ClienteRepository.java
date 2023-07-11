package com.transaction.devsu.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDTO, Long> {
    Optional<ClienteDTO> findClienteDTOByIdentificacion(String identificacion);
}
