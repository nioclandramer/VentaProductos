package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioDto;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioToSaveDTO;

public interface DetalleEnvioService {
    DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioToSaveDTO detalleEnvio);
    DetalleEnvioDto actualizarDetalleEnvio(DetalleEnvioToSaveDTO detalleEnvio);
    DetalleEnvioDto findById(Long id);
    void deleteById(Long id);
}
