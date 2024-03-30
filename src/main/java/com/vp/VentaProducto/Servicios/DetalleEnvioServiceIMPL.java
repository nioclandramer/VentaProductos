package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioDto;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioMapper;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioToSaveDTO;
import com.vp.VentaProducto.Entidades.DetalleEnvio;
import com.vp.VentaProducto.Exception.DetalleEnvioNotFoundException;
import com.vp.VentaProducto.Repositorios.DetalleEnvioRepository;
import org.springframework.stereotype.Service;

@Service
public class DetalleEnvioServiceIMPL implements DetalleEnvioService{
    private final DetalleEnvioRepository detalleEnvioRepository;

    public DetalleEnvioServiceIMPL(DetalleEnvioRepository detalleEnvioRepository) {
        this.detalleEnvioRepository = detalleEnvioRepository;
    }

    @Override
    public DetalleEnvioDto guardarDetalleEnvio(DetalleEnvioToSaveDTO detalleEnvio) {
        DetalleEnvio detalleEnvio1= DetalleEnvioMapper.INSTANCE.toEntity(detalleEnvio);
        DetalleEnvio detalleEnvioGuardado=detalleEnvioRepository.save(detalleEnvio1);
        return DetalleEnvioMapper.INSTANCE.detalleEnvioToDto(detalleEnvioGuardado);
    }

    @Override
    public DetalleEnvioDto actualizarDetalleEnvio(DetalleEnvioToSaveDTO detalleEnvio) {
        DetalleEnvio detalleEnvio1= DetalleEnvioMapper.INSTANCE.toEntity(detalleEnvio);
        DetalleEnvio detalleEnvioExiste=detalleEnvioRepository.findById(detalleEnvio.id()).orElseThrow(()-> new DetalleEnvioNotFoundException("No se encontro el Detalle de Envio"));
        detalleEnvioExiste.setNumeroGuia(detalleEnvio1.getNumeroGuia());
        detalleEnvioExiste.setDireccion(detalleEnvio1.getDireccion());
        detalleEnvioExiste.setTransportadora(detalleEnvio1.getTransportadora());
        detalleEnvioExiste.setPedido(detalleEnvio1.getPedido());
        detalleEnvioExiste=detalleEnvioRepository.save(detalleEnvioExiste);
        return DetalleEnvioMapper.INSTANCE.detalleEnvioToDto(detalleEnvioExiste);
    }

    @Override
    public DetalleEnvioDto findById(Long id) {
        DetalleEnvio detalleEnvio=detalleEnvioRepository.findById(id).orElseThrow(()-> new DetalleEnvioNotFoundException("No se encontro el Detalle de Envio"));
        return DetalleEnvioMapper.INSTANCE.detalleEnvioToDto(detalleEnvio);
    }

    @Override
    public void deleteById(Long id) {
        DetalleEnvio detalleEnvio=detalleEnvioRepository.findById(id).orElseThrow(()-> new DetalleEnvioNotFoundException("No se encontro el Detalle de Envio"));
        detalleEnvioRepository.delete(detalleEnvio);
    }
}
