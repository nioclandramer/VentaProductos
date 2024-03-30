package com.vp.VentaProducto.Dtos.Cliente;

import com.vp.VentaProducto.Entidades.Cliente;
import org.mapstruct.factory.Mappers;

public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    ClienteDto clienteToDto(Cliente cliente);
    Cliente dtoToCliente(ClienteDto clienteDto);
    Cliente toEntity(ClienteToSaveDto clienteToSaveDto);
}
