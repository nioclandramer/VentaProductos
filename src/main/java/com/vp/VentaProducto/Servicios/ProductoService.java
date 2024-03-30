package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Producto.ProductoDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoToSaveDto;

public interface ProductoService {
    ProductoDto guardarProducto(ProductoToSaveDto producto);
    ProductoDto actualizarProducto(ProductoToSaveDto producto);
    ProductoDto findById(Long id);
    void deleteById(Long id);
}
