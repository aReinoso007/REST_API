package com.transaction.devsu.service;

import com.transaction.devsu.dto.mappers.AccountMapper;
import com.transaction.devsu.dto.mappers.AccountMapperImpl;
import com.transaction.devsu.repository.AccountRepository;
import com.transaction.devsu.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private AccountService accountService;

    @InjectMocks
    private AccountMapper accountMapper= new AccountMapperImpl();



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountService(accountRepository, accountMapper, clienteRepository);
    }


    @Test
    void findAll() {
        when(accountService.findAll()).thenReturn(Arrays.asList());
        assertNotNull(accountService.findAll());
    }




}