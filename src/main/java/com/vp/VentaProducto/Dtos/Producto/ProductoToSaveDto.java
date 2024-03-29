package com.vp.VentaProducto.Dtos.Producto;

public record ProductoToSaveDto(Long id,
                                String nombre,
                                Integer precio,
                                Integer stock) {
}
