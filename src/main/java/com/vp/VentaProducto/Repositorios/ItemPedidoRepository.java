package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.Entidades.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {
    Optional<List<ItemPedido>> findByPedidoId(Long pedidoId);
    Optional<List<ItemPedido>> findByProductoId(Long productoId);
    @Query("SELECT SUM(ip.cantida*ip.precioUnitario) FROM ItemPedido ip WHERE ip.producto.id= :productoId")
    Optional<Integer> totalVentasPorProducto(@Param("productoId")Long prouctoId);
}
