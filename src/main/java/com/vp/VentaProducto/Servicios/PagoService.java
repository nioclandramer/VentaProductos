package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Pago.PagoDto;
import com.vp.VentaProducto.Dtos.Pago.PagoToSaveDto;

public interface PagoService {
    PagoDto guardarPago(PagoToSaveDto pago);
    PagoDto actualizarPago(PagoToSaveDto pago);
    PagoDto findById(Long id);
    void deleteById(Long id);
}
