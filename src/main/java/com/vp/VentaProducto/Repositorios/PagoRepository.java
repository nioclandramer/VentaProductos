package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.Entidades.MetodoDePago;
import com.vp.VentaProducto.Entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago,Long> {
    Optional<List<Pago>> findByFechaPagoBetween(LocalDateTime fechaInicio,LocalDateTime fechaFinal);
    Optional<Pago> findByIdAndMetodoDePago(Long pagoId, MetodoDePago metodoDePago);
}
