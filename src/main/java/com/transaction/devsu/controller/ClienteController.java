package com.transaction.devsu.controller;

import com.transaction.devsu.dto.ClientDTO;
import com.transaction.devsu.service.ClientService;
import com.transaction.devsu.utils.ResponseHandler;
import com.transaction.devsu.utils.messages.Response;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<?> save(@RequestBody() ClientDTO clientDTO) {
        try{

            return new ResponseEntity<>(clientService.addNewCliente(clientDTO), Response.HTTP_STATUS_CREATED);
        }catch (Exception e) {
            String message = "";
            if(e.getCause()!=null){
                log.error("constraints"+ e.getCause().getCause().getMessage());
                ConstraintViolationException cons = (ConstraintViolationException) e.getCause().getCause();
                message = cons.getConstraintViolations().stream().iterator().next().getMessageTemplate();
            }else message= e.getMessage();
            return ResponseHandler.generateResponse(message, Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable("id") String id){
        try{
            if(clientService.deleteById(Long.valueOf(id))){
                return new ResponseEntity<>(Response.SUCCESS, HttpStatus.OK);
            }else return ResponseHandler.generateResponse(Response.CLIENT_NOT_FOUND, Response.HTTP_STATUS_NOT_FOUND, null);
        }catch (Exception e){
            log.error("delete exception "+e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }




}
