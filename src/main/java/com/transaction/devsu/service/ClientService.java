package com.transaction.devsu.service;

import com.transaction.devsu.dto.mappers.ClientMapper;
import com.transaction.devsu.entities.Client;
import com.transaction.devsu.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClienteRepository clienteRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClienteRepository clienteRepository, ClientMapper clientMapper){
        this.clienteRepository = clienteRepository;
        this.clientMapper = clientMapper;
    }

    public void addNewCliente(Client client){

        Optional<Client> clienteOptional = clienteRepository.findClientByIdentification(client.getIdentification());
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
