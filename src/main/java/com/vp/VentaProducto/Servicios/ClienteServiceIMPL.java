package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteMapper;
import com.vp.VentaProducto.Dtos.Cliente.ClienteToSaveDto;
import com.vp.VentaProducto.Entidades.Cliente;
import com.vp.VentaProducto.Exception.ClienteNotFoundException;
import com.vp.VentaProducto.Repositorios.ClienteRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceIMPL implements ClienteService{
    private final ClienteRepository clienteRepository;

    public ClienteServiceIMPL(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDto guardarCliente(ClienteToSaveDto cliente) {
        Cliente cliente1= ClienteMapper.INSTANCE.toEntity(cliente);
        Cliente clienteGuardado=clienteRepository.save(cliente1);
        return ClienteMapper.INSTANCE.clienteToDto(clienteGuardado);
    }

    @Override
    public ClienteDto actualizarCliente(ClienteToSaveDto cliente) {
        Cliente cliente1=ClienteMapper.INSTANCE.toEntity(cliente);
        Cliente clienteExiste=clienteRepository.findById(cliente.id()).orElseThrow(()-> new ClienteNotFoundException("Cliente no existente"));
        clienteExiste.setNombre(cliente1.getNombre());
        clienteExiste.setDireccion(cliente1.getDireccion());
        clienteExiste.setEmail(cliente1.getEmail());
        clienteExiste.setPedidos(cliente1.getPedidos());
        clienteExiste=clienteRepository.save(clienteExiste);
        return ClienteMapper.INSTANCE.clienteToDto(clienteExiste);
    }

    @Override
    public ClienteDto findById(Long id) throws ClienteNotFoundException {
        Cliente cliente=clienteRepository.findById(id).orElseThrow(()-> new ClienteNotFoundException("Cliente no existente"));
        return ClienteMapper.INSTANCE.clienteToDto(cliente);
    }

    @Override
    public void deleteByID(Long id) throws ClienteNotFoundException {
        Cliente cliente=clienteRepository.findById(id).orElseThrow(()-> new ClienteNotFoundException("Cliente no existente"));
        clienteRepository.delete(cliente);
    }
}
