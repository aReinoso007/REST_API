package com.transaction.devsu.repository;

import com.transaction.devsu.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Transactional
    void deleteAccountByAccountNumber(String accountNumber);

}
