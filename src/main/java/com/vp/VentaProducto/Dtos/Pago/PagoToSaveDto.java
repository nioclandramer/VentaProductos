package com.vp.VentaProducto.Dtos.Pago;

import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Entidades.MetodoDePago;
import com.vp.VentaProducto.Entidades.Pedido;

import java.time.LocalDateTime;

public record PagoToSaveDto(Long id,
                            Float totalPago,
                            LocalDateTime fechaPago,
                            MetodoDePago metodoDePago,
                            PedidoDto pedido) {
}
