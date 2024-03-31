package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioDto;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioToSaveDTO;
import com.vp.VentaProducto.Entidades.EstatusPedido;

import java.util.List;
import java.util.Optional;

public interface DetalleEnvioService {
    DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioToSaveDTO detalleEnvio);
    DetalleEnvioDto actualizarDetalleEnvio(DetalleEnvioToSaveDTO detalleEnvio);
    DetalleEnvioDto findById(Long id);
    void deleteById(Long id);
    Optional<DetalleEnvioDto> findByPedidoId(Long pedidoId);
    Optional<List<DetalleEnvioDto>> findByTransportadora(String transportadora);
    Optional<List<DetalleEnvioDto>> findByEstado(EstatusPedido estado);

}
