package com.vp.VentaProducto.Dtos.ItemPedido;

import com.vp.VentaProducto.Dtos.Producto.ProductoDto;

public record ItemPedidoDto(Long id,
                            Integer cantida,
                            Integer precioUnitario,
                            ProductoDto productoDto) {

}
