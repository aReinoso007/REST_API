package com.transaction.devsu.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        Optional<Client> clienteOptional = clienteRepository.findClienteByIdentificacion(client.getIdentificacion());
        if(clienteOptional.isPresent()){
            throw new IllegalStateException("Client with the given identificacion has already been registered");
        }
        clienteRepository.save(client);
    }

    public List<ClientDTO> getClientes(){
        List<ClientDTO> clientDTO = new ArrayList<>();
        List<Client> clients = clienteRepository.findAll();
        if(!clients.isEmpty()){
            for(Client client : clients){
                clientDTO.add(new ClientDTO(client));
            }
        }
        return clientDTO;
    }

}
