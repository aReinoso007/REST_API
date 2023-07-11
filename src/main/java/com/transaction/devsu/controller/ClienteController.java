package com.transaction.devsu.controller;

import com.transaction.devsu.dto.ClientDTO;
import com.transaction.devsu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<ClientDTO> getClientes(){
        return clientService.getAllClients();
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody() ClientDTO clientDTO){
        return null;
    }

}
