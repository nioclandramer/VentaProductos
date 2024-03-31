package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoToSaveDto;
import com.vp.VentaProducto.Entidades.Cliente;
import com.vp.VentaProducto.Entidades.EstatusPedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    PedidoDto guardarPedido(PedidoToSaveDto pedido);
    PedidoDto actualizarPedido(PedidoToSaveDto pedido);
    PedidoDto findById(Long id);
    void deleteById(Long id);

    Optional<List<PedidoDto>> findByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    Optional<List<PedidoDto>> findByClienteAndEstado(Long clienteId, EstatusPedido estatusPedido);
    Optional<List<PedidoDto>>findByClienteWhithItemPedidos(Cliente cliente);
}
