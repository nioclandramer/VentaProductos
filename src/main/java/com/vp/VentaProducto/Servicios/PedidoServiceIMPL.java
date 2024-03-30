package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoMapper;
import com.vp.VentaProducto.Dtos.Pedido.PedidoToSaveDto;
import com.vp.VentaProducto.Entidades.Pedido;
import com.vp.VentaProducto.Exception.PedidoNotFoundException;
import com.vp.VentaProducto.Repositorios.PedidoRepository;
import org.springframework.stereotype.Service;

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
        pedidoExiste.setItemPedidos(pedido1.getItemPedidos());
        pedidoExiste.setDetalleEnvio(pedido1.getDetalleEnvio());
        pedidoExiste.setPago(pedido1.getPago());
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
}
