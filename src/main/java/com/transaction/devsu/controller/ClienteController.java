package com.transaction.devsu.controller;

import com.transaction.devsu.entities.Client;
import com.transaction.devsu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    private final ClientService clientService;

    @Autowired
    public ClienteController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("")
    public List<Client> getClientes(){
        return clientService.getClientes();
    }

}
