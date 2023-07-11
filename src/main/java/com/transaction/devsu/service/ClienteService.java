package com.transaction.devsu.service;

import com.transaction.devsu.entities.Client;
import com.transaction.devsu.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public void addNewCliente(Client client){

        Optional<Client> clienteOptional = clienteRepository.findClienteByIdentificacion(client.getIdentification());
        if(clienteOptional.isPresent()){
            throw new IllegalStateException("Client with the given identificacion has already been registered");
        }
        clienteRepository.save(client);
    }

    public List<Client> getClientes(){

        List<Client> clients = clienteRepository.findAll();

        return clients;
    }

}
