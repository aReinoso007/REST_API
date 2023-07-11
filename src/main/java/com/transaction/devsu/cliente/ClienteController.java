package com.transaction.devsu.cliente;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @GetMapping("")
    public List<ClienteDTO> getClientes(){

    }

}
