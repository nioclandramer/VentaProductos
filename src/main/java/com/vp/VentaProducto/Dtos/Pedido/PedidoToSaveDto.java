package com.vp.VentaProducto.Dtos.Pedido;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Entidades.EstatusPedido;

import java.time.LocalDateTime;

public record PedidoToSaveDto(Long id,
                              LocalDateTime fechaPedido,
                              EstatusPedido estado,
                              ClienteDto cliente) {
}
