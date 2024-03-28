package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository <Producto,Long>{
    List<Producto> findByNombreContainingIgnoreCase(String termino);
    @Query("SELECT p FROM Producto p WHERE p.Stock >= :num")
    Optional<List<Producto>> findByProductStockEqual(Integer num);
    @Query("SELECT p FROM Producto p WHERE p.precio <= :precio AND p.Stock <= :stock")
    Optional<List<Producto>> findByPrecioAndStock(Integer precio, Integer stock);
    //sdasdadsd
}
