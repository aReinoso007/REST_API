package com.transaction.devsu.service;

import com.transaction.devsu.dto.ClientDTO;
import com.transaction.devsu.dto.mappers.ClientMapper;
import com.transaction.devsu.dto.mappers.ClientMapperImpl;
import com.transaction.devsu.entities.Client;
import com.transaction.devsu.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @InjectMocks
    private ClientMapper clientMapper = new ClientMapperImpl();
    @InjectMocks
    private ClientService clientService;

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
        clientService = new ClientService(clienteRepository, clientMapper);
    }

    @Test
    void addNewCliente() {
        when(this.clienteRepository.save(any(Client.class))).thenReturn(clientMapper.clientDTOToClient(clientDTO));
        ClientDTO clientDTOSaved = this.clientService.addNewCliente(clientDTO);
        assertNotNull(clientDTOSaved);
    }
}