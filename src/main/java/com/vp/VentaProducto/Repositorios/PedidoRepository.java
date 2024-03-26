package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.Entidades.Cliente;
import com.vp.VentaProducto.Entidades.EstatusPedido;
import com.vp.VentaProducto.Entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
    Optional<List<Pedido>> findByFechaPedidoBetween(LocalDateTime fechaInicio,LocalDateTime fechaFin);
    Optional<List<Pedido>> findByClienteAndEstado(Long clienteId, EstatusPedido estatusPedido);
    @Query("SELECT p from Pedido p JOIN FETCH p.itemPedidos WHERE p.cliente = :cliente")
    Optional<List<Pedido>> findByClienteWhithItemPedidos(Cliente cliente);

}
