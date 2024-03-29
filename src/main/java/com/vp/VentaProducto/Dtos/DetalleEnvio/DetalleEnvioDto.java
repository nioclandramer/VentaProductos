package com.vp.VentaProducto.Dtos.DetalleEnvio;

public record DetalleEnvioDto(Long id,
                              String direccion,
                              String transportadora,
                              Integer numeroGuia) {
}
