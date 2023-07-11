package com.transaction.devsu.service;

import com.transaction.devsu.dto.mappers.AccountMapper;
import com.transaction.devsu.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    private AccountService(AccountRepository accountRepository, AccountMapper accountMapper){
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

}
