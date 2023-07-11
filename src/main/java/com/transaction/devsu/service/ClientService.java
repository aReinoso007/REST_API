package com.transaction.devsu.service;

import com.transaction.devsu.dto.ClientDTO;
import com.transaction.devsu.dto.mappers.ClientMapper;
import com.transaction.devsu.entities.Client;
import com.transaction.devsu.repository.ClienteRepository;
import com.transaction.devsu.utils.BeanNullPropChecker;
import com.transaction.devsu.utils.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ClientService {

    private final ClienteRepository clienteRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClienteRepository clienteRepository, ClientMapper clientMapper){
        this.clienteRepository = clienteRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDTO> getAllClients(){
        try{
            Optional<List<Client>> clients = Optional.of(clienteRepository.findAll());
            return clientMapper.toClientDTOs(clients.orElseGet(ArrayList::new));
        }catch (Exception e){
            log.error("Exception at ClientService while getting all");
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public ClientDTO findByClientId(Long id){
        try{
            Optional<Client> client = clienteRepository.findById(id);
            return clientMapper.toClientDTO(client.orElseThrow(()-> new IllegalStateException("CLIENT NOT FOUND")));
        }catch (Exception e){
            log.error("Error at ClientService.findByClientId");
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public synchronized ClientDTO addNewCliente(ClientDTO clientDTO) throws CustomException {

        try{
            Optional<Client> clienteOptional = clienteRepository.findClientByIdentification(clientDTO.getCedula());
            if(clienteOptional.isPresent()){
                throw new CustomException("Client with the given identificacion has already been registered");
            }
            return clientMapper.toClientDTO(clienteRepository.save(clientMapper.toClient(clientDTO)));
        }catch (Exception e){
            log.error("Error saving client");
            throw  new CustomException(e.getMessage().toString(), e.getCause());
        }
    }


    public ClientDTO updateClientData(ClientDTO clientDTO, String identificacion){
        try {
            Client client = clienteRepository.findClientByIdentification(identificacion)
                    .orElseThrow(()-> new IllegalStateException("Client not found"));
            Client clientDataToUpdate = clientMapper.toClient(clientDTO);
            BeanUtilsBean beanUtilsBean = new BeanNullPropChecker();
            beanUtilsBean.copyProperties(client, clientDataToUpdate);
            return clientMapper.toClientDTO(clienteRepository.save(client));
        }catch (Exception e){
            log.error("error at updateClientData of ClientService");
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public Boolean deleteById(long id){
        try{
            if(clienteRepository.findById(id).isEmpty()) throw new IllegalStateException("Client with given ID not found");
            clienteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.error("error deleting by id at ClientService");
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
