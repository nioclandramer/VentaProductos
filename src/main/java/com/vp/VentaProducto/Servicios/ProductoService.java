package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Producto.ProductoDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoToSaveDto;
import com.vp.VentaProducto.Entidades.Producto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    ProductoDto guardarProducto(ProductoToSaveDto producto);
    ProductoDto actualizarProducto(ProductoToSaveDto producto);
    ProductoDto findById(Long id);
    void deleteById(Long id);
    Optional<List<ProductoDto>> findByNombreContainingIgnoreCase(String termino);

    Optional<List<ProductoDto>> findByProductStockEqual(Integer num);

    Optional<List<ProductoDto>> findByPrecioAndStock(Integer precio, Integer stock);
}
