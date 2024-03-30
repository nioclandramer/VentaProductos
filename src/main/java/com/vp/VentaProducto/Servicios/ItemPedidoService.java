package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoDto;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoToSaveDto;

public interface ItemPedidoService {
    ItemPedidoDto guardarItemPedido(ItemPedidoToSaveDto itemPedido);
    ItemPedidoDto actualizarItemPedido(ItemPedidoToSaveDto itemPedido);
    ItemPedidoDto findById(Long id);
    void deleteById(Long id);
}
