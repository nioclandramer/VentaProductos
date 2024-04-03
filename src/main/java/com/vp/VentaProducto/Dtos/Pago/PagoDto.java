package com.vp.VentaProducto.Dtos.Pago;

import com.vp.VentaProducto.Entidades.MetodoDePago;

import java.time.LocalDateTime;

public record PagoDto(Long id,
                      Float totalPago,
                      LocalDateTime fechaPago,
                      MetodoDePago metodoDePago) {

}
