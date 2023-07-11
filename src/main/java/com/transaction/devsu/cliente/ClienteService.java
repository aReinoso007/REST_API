package com.transaction.devsu.cliente;

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

    public void addNewCliente(Cliente cliente){

        Optional<Cliente> clienteOptional = clienteRepository.findClienteByIdentificacion(cliente.getIdentificacion());
        if(clienteOptional.isPresent()){
            throw new IllegalStateException("Client with the given identificacion has already been registered");
        }
        clienteRepository.save(cliente);
    }

    public List<ClienteDTO> getClientes(){
        List<ClienteDTO> clienteDTO = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAll();
        for(Cliente cliente : clientes){
            clienteDTO.add(new ClienteDTO(cliente));
        }
        return clienteDTO;
    }

}
