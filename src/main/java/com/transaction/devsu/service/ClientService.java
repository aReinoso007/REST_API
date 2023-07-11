package com.transaction.devsu.service;

import com.transaction.devsu.dto.ClientDTO;
import com.transaction.devsu.dto.mappers.ClientMapper;
import com.transaction.devsu.entities.Client;
import com.transaction.devsu.repository.ClienteRepository;
import com.transaction.devsu.utils.BeanNullPropChecker;
import com.transaction.devsu.utils.CustomException;
import com.transaction.devsu.utils.messages.Response;
import jakarta.validation.ConstraintViolationException;
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
            return clientMapper.toClientDTO(client.orElseThrow(()-> new CustomException(Response.CLIENT_NOT_FOUND)));
        }catch (Exception e){
            log.error("Error at ClientService.findByClientId");
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public synchronized ClientDTO addNewCliente(ClientDTO clientDTO) throws CustomException {

        try{
            Optional<Client> clienteOptional = clienteRepository.findClientByIdentification(clientDTO.getCedula());
            if(clienteOptional.isPresent()){
                throw new CustomException(Response.CLIENT_EXISTS);
            }
            return clientMapper.toClientDTO(clienteRepository.save(clientMapper.toClient(clientDTO)));
        }catch (ConstraintViolationException cve){
            log.error(cve.getMessage());
            throw new CustomException(cve.getMessage().toString(), cve.getCause());
        }catch (Exception e){
            log.error(e.getMessage());
            throw  new CustomException(e.getMessage().toString(), e.getCause());
        }
    }


    public ClientDTO updateClientData(ClientDTO clientDTO, String identificacion) throws CustomException {
        try {
            Client client = clienteRepository.findClientByIdentification(identificacion)
                    .orElseThrow(()-> new CustomException(Response.CLIENT_NOT_FOUND));
            Client clientDataToUpdate = clientMapper.toClient(clientDTO);
            BeanUtilsBean beanUtilsBean = new BeanNullPropChecker();
            beanUtilsBean.copyProperties(client, clientDataToUpdate);
            return clientMapper.toClientDTO(clienteRepository.save(client));
        }catch (Exception e){
            log.error("error at updateClientData of ClientService");
            throw new CustomException(e.getMessage().toString(), e.getCause());
        }
    }

    public Boolean deleteById(long id){
        try{
            if(clienteRepository.findById(id).isEmpty()) throw new CustomException(Response.CLIENT_NOT_FOUND);
            clienteRepository.deleteById(id);
            return true;
        }catch (Exception e){
            log.error("error deleting by id at ClientService");
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

}
