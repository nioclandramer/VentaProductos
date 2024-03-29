package com.vp.VentaProducto.Dtos.DetalleEnvio;

import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;

public record DetalleEnvioToSaveDTO(Long id, String direccion, String transportadora, Integer numeroGuia, PedidoDto pedidoDto) {
}
