package com.transaction.devsu.controller;

import com.transaction.devsu.dto.ClientDTO;
import com.transaction.devsu.service.ClientService;
import com.transaction.devsu.utils.ResponseHandler;
import com.transaction.devsu.utils.Util;
import com.transaction.devsu.utils.messages.Response;
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
            String message = Util.getConstraintViolationsFromException(e);
            return ResponseHandler.generateResponse(message, Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }

    @PatchMapping(path = "/{cedula}")
    public ResponseEntity<?> updateCliente(@PathVariable("cedula") String cedula, @RequestParam String nombre){
        try{
            ClientDTO responseObject = clientService.updateClientName(nombre, cedula);
            return ResponseHandler.generateResponse(Response.SUCCESS, Response.HTTP_STATUS_OK, responseObject);
        }catch (Exception e){
            log.info("returning exception "+e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }


    @DeleteMapping(path = "/{cedula}")
    public ResponseEntity<?> deleteClientById(@PathVariable("cedula") String cedula){
        try{
            if(clientService.deleteClientByCedula(cedula)){
                return new ResponseEntity<>(Response.SUCCESS, HttpStatus.OK);
            }else return ResponseHandler.generateResponse(Response.CLIENT_NOT_FOUND, Response.HTTP_STATUS_NOT_FOUND, cedula);
        }catch (Exception e){
            log.error("delete exception "+e.getCause().getCause().getMessage());
            return ResponseHandler.generateResponse(e.getMessage(), Response.HTTP_STATUS_BAD_REQUEST, null);
        }
    }




}
