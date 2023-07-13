package com.transaction.devsu.service;

import com.transaction.devsu.dto.ClientDTO;
import com.transaction.devsu.entities.Client;
import com.transaction.devsu.repository.AccountRepository;
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
    @InjectMocks
    private AccountService accountService;

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clientDTO = ClientDTO.builder()
                .cedula("1500767403")
                .nombre("Dev Su Mock Testing")
                .genero("MALE")
                .edad(25)
                .direccion("Direccion Test")
                .numeroTelefono("0889876728")
                .contrasena("Abc123!df")
                .estado(true).build();
    }


    @Test
    void findAll() {
        when(accountService.findAll()).thenReturn(Arrays.asList());
        assertNotNull(accountService.findAll());
    }
}