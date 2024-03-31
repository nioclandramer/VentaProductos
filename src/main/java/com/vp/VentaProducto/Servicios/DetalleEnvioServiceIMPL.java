package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioDto;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioMapper;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioToSaveDTO;
import com.vp.VentaProducto.Entidades.DetalleEnvio;
import com.vp.VentaProducto.Entidades.EstatusPedido;
import com.vp.VentaProducto.Exception.DetalleEnvioNotFoundException;
import com.vp.VentaProducto.Repositorios.DetalleEnvioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Optional<DetalleEnvioDto> findByPedidoId(Long pedidoId) {
        DetalleEnvio detalleEnvio= detalleEnvioRepository.findByPedidoId(pedidoId)
                .orElseThrow(()->new DetalleEnvioNotFoundException("No se encontró detalle del envio"));
        return Optional.of(DetalleEnvioMapper.INSTANCE.detalleEnvioToDto(detalleEnvio));
    }

    @Override
    public Optional<List<DetalleEnvioDto>> findByTransportadora(String transportadora) {
        List<DetalleEnvio> detalleEnvios= detalleEnvioRepository.findByTransportadora(transportadora)
                .orElseThrow(()-> new DetalleEnvioNotFoundException("No se encontró detalle del envio"));
        List<DetalleEnvioDto> detalleEnvioDtos= detalleEnvios.stream().map(DetalleEnvioMapper.INSTANCE::detalleEnvioToDto)
                .collect(Collectors.toList());
        return Optional.of(detalleEnvioDtos);
    }

    @Override
    public Optional<List<DetalleEnvioDto>> findByEstado(EstatusPedido estado) {
        List<DetalleEnvio> detalleEnvios= detalleEnvioRepository.findByestado(estado)
                .orElseThrow(()-> new DetalleEnvioNotFoundException("No se encontró detalle del envio"));
        List<DetalleEnvioDto> detalleEnvioDtos=detalleEnvios.stream().map(DetalleEnvioMapper.INSTANCE::detalleEnvioToDto)
                .collect(Collectors.toList());

        return Optional.of(detalleEnvioDtos);
    }

    @Override
    public Optional<List<DetalleEnvioDto>> getDestalleEnvio() {
        List<DetalleEnvio> detalleEnvio=detalleEnvioRepository.findAll();
        List<DetalleEnvioDto> detalleEnvioDtos=detalleEnvio.stream().map(DetalleEnvioMapper.INSTANCE::detalleEnvioToDto).collect(Collectors.toList());
        return Optional.of(detalleEnvioDtos);
    }
}
