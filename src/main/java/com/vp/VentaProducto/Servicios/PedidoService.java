package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoToSaveDto;

public interface PedidoService {
    PedidoDto guardarPedido(PedidoToSaveDto pedido);
    PedidoDto actualizarPedido(PedidoToSaveDto pedido);
    PedidoDto findById(Long id);
    void deleteById(Long id);
}
