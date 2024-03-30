package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteToSaveDto;
import com.vp.VentaProducto.Exception.ClienteNotFoundException;

public interface ClienteService {
    ClienteDto guardarCliente(ClienteToSaveDto cliente);
    ClienteDto actualizarCliente(ClienteToSaveDto cliente);
    ClienteDto findById(Long id)throws ClienteNotFoundException;
    void deleteByID(Long id)throws ClienteNotFoundException;
}
