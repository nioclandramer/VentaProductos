package com.vp.VentaProducto.Dtos.Cliente;

import com.vp.VentaProducto.Entidades.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    ClienteDto clienteToDto(Cliente cliente);
    Cliente dtoToCliente(ClienteDto clienteDto);
    Cliente toEntity(ClienteToSaveDto clienteToSaveDto);
}
