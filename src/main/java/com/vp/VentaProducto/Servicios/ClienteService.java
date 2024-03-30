package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteToSaveDto;
import com.vp.VentaProducto.Exception.ClienteNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    ClienteDto guardarCliente(ClienteToSaveDto cliente);
    ClienteDto actualizarCliente(ClienteToSaveDto cliente);
    ClienteDto findById(Long id)throws ClienteNotFoundException;
    void deleteByID(Long id)throws ClienteNotFoundException;
    Optional<ClienteDto> findByEmail(String email);
}
