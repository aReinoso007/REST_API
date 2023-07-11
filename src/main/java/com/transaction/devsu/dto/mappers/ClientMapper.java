package com.transaction.devsu.dto.mappers;

import com.transaction.devsu.entities.Client;
import com.transaction.devsu.dto.ClientDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "gender", target = "genero")
    @Mapping(source = "age", target = "edad")
    @Mapping(source = "identification", target = "cedula")
    @Mapping(source = "address", target = "direccion")
    @Mapping(source = "phoneNumber", target = "numeroTelefono")
    @Mapping(source = "password", target = "contrasena")
    @Mapping(source = "status", target = "estado")
    ClientDTO toClientDTO(Client client);
    List<ClientDTO> toClientDTOs(List<Client> clients);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accounts", ignore = true)
    Client toClient(ClientDTO clientDTO);
}
