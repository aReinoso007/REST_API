package com.transaction.devsu.repository;

import com.transaction.devsu.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByIdentification(String identification);
    @Transactional
    public void deleteClientByIdentification(String identification);

    @Query(value = "SELECT * FROM DEV_CLIENTS \n" +
            "WHERE  per_identification = :identification", nativeQuery = true)
    Optional<Client> getClientUsingIdentification(String identification);

    Optional<Client> findByIdentification(String identification);
}
