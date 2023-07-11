package com.transaction.devsu.controller;

import com.transaction.devsu.dto.ClientDTO;
import com.transaction.devsu.service.ClientService;
import com.transaction.devsu.utils.CustomException;
import com.transaction.devsu.utils.messages.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
@Slf4j
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
        try{
            return new ResponseEntity<>(clientService.addNewCliente(clientDTO), Response.HTTP_STATUS_CREATED);
        }catch (Exception e){
            log.error("error saving client ");
            //throw new CustomException(e.getCause());
        }
        return null;
    }

}
