package com.vp.VentaProducto.Dtos.ItemPedido;

import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoDto;

public record ItemPedidoToSaveDto(Long id, Integer cantida, Integer precioUnitario, PedidoDto pedidoDto, ProductoDto productoDto) {
}
