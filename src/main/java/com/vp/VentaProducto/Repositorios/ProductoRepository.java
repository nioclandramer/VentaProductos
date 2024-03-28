package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository <Producto,Long>{
    List<Producto> findByNombreContainingIgnoreCase(String termino);
    List<Producto> findByStockGreaterThanEqual(Integer num);
    List<Producto> findByPrecioLessThanEqualAndStockLessThanEqual(Integer precio,Integer stock);
}
