package com.vp.VentaProducto.Dtos.Producto;

public record ProductoDto(Long id,
                          String nombre,
                          Integer precio,
                          Integer Stock) {
}
