package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteMapper;
import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoMapper;
import com.vp.VentaProducto.Dtos.Pedido.PedidoToSaveDto;
import com.vp.VentaProducto.Entidades.Cliente;
import com.vp.VentaProducto.Entidades.EstatusPedido;
import com.vp.VentaProducto.Entidades.Pedido;
import com.vp.VentaProducto.Exception.PedidoNotFoundException;
import com.vp.VentaProducto.Repositorios.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceIMPL implements PedidoService{
    private final PedidoRepository pedidoRepository;

    public PedidoServiceIMPL(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PedidoDto guardarPedido(PedidoToSaveDto pedido) {
        Pedido pedido1= PedidoMapper.INSTANCE.ToEntity(pedido);
        Pedido pedidoGuardado=pedidoRepository.save(pedido1);
        return PedidoMapper.INSTANCE.pedidoToDto(pedidoGuardado);
    }

    @Override
    public PedidoDto actualizarPedido(PedidoToSaveDto pedido) {
        Pedido pedido1= PedidoMapper.INSTANCE.ToEntity(pedido);
        Pedido pedidoExiste=pedidoRepository.findById(pedido.id()).orElseThrow(()->new PedidoNotFoundException("Pedido No Encontrado"));
        pedidoExiste.setFechaPedido(pedido1.getFechaPedido());
        pedidoExiste.setEstado(pedido1.getEstado());
        pedidoExiste.setCliente(pedido1.getCliente());
        pedidoExiste=pedidoRepository.save(pedidoExiste);
        return PedidoMapper.INSTANCE.pedidoToDto(pedidoExiste);
    }

    @Override
    public PedidoDto findById(Long id) {
        Pedido pedido=pedidoRepository.findById(id).orElseThrow(()->new PedidoNotFoundException("Pedido No Encontrado"));
        return PedidoMapper.INSTANCE.pedidoToDto(pedido);
    }

    @Override
    public void deleteById(Long id) {
        Pedido pedido=pedidoRepository.findById(id).orElseThrow(()->new PedidoNotFoundException("Pedido No Encontrado"));
        pedidoRepository.delete(pedido);
    }

    @Override
    public Optional<List<PedidoDto>> findByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
       List<Pedido> pedidos= pedidoRepository.findByFechaPedidoBetween(fechaInicio,fechaFin)
               .orElseThrow(()-> new PedidoNotFoundException("No se encontaron pedidos en dichas fechas"));
       List<PedidoDto>pedidoDtos=pedidos.stream().map(PedidoMapper.INSTANCE::pedidoToDto)
               .collect(Collectors.toList());

        return Optional.of(pedidoDtos);
    }

    @Override
    public Optional<List<PedidoDto>> findByClienteAndEstado(Long clienteId, EstatusPedido estatusPedido) {
      List<Pedido> pedidos= pedidoRepository.findByClienteAndEstado(clienteId,estatusPedido)
              .orElseThrow(()-> new PedidoNotFoundException("No se encontraron pedidos"));
      List<PedidoDto>pedidoDtos=pedidos.stream().map(PedidoMapper.INSTANCE::pedidoToDto).collect(Collectors.toList());

        return Optional.of(pedidoDtos);
    }

    @Override
    public Optional<List<PedidoDto>> findByClienteWhithItemPedidos(ClienteDto cliente1) {
        Cliente cliente = ClienteMapper.INSTANCE.dtoToCliente(cliente1);
       List<Pedido>pedidos=pedidoRepository.findByClienteWhithItemPedidos(cliente)
               .orElseThrow(()-> new PedidoNotFoundException("No se encontraon pedidos"));
       List<PedidoDto> pedidoDtos=pedidos.stream().map(PedidoMapper.INSTANCE::pedidoToDto)
               .collect(Collectors.toList());
        return Optional.of(pedidoDtos);
    }

    @Override
    public Optional<List<PedidoDto>> getAllPedido() {
        List<Pedido> pedido=pedidoRepository.findAll();
        List<PedidoDto> pedidoDto=pedido.stream().map(PedidoMapper.INSTANCE::pedidoToDto).collect(Collectors.toList());
        return Optional.of(pedidoDto);
    }
}
