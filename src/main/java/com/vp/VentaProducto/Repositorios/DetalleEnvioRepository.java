package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.Entidades.DetalleEnvio;
import com.vp.VentaProducto.Entidades.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DetalleEnvioRepository extends JpaRepository<DetalleEnvio, Long> {
    Optional<DetalleEnvio> findByPedidoId(Long pedidoId);
    Optional<List<DetalleEnvio>> findByTransportadora(String transportadora);
    @Query("SELECT de FROM DetalleEnvio de INNER JOIN de.pedido p WHERE p.estado= :estado")
    Optional<List<DetalleEnvio>> findByestado(@Param("estado")String estado);
}
