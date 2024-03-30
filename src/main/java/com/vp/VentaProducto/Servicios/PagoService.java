package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Pago.PagoDto;
import com.vp.VentaProducto.Dtos.Pago.PagoToSaveDto;
import com.vp.VentaProducto.Entidades.MetodoDePago;
import com.vp.VentaProducto.Entidades.Pago;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PagoService {
    PagoDto guardarPago(PagoToSaveDto pago);
    PagoDto actualizarPago(PagoToSaveDto pago);
    PagoDto findById(Long id);
    void deleteById(Long id);
    Optional<List<PagoDto>> findByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFinal);
    Optional<PagoDto> findByIdAndMetodoDePago(Long Id, MetodoDePago metodoDePago);
}
