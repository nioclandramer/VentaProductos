package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoDto;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoToSaveDto;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoService {
    ItemPedidoDto guardarItemPedido(ItemPedidoToSaveDto itemPedido);
    ItemPedidoDto actualizarItemPedido(ItemPedidoToSaveDto itemPedido);
    ItemPedidoDto findById(Long id);
    void deleteById(Long id);
    Optional<List<ItemPedidoDto>> findByPedidoId(Long pedidoId);
    Optional<List<ItemPedidoDto>> findByProductoId(Long productoId);
    Optional<Integer> totalVentasPorProducto(Long ProductoId);
}
