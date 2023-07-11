package com.transaction.devsu.dto.mappers;

import com.transaction.devsu.entities.Client;
import com.transaction.devsu.dto.ClientDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "identification", target = "identification")
    ClientDTO toClientDTO(Client client);
    List<ClientDTO> toClientDTOs(List<Client> clients);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cuentas", ignore = true)
    Client toClient(ClientDTO clientDTO);
}
