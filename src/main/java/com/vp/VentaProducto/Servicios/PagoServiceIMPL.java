package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Pago.PagoDto;
import com.vp.VentaProducto.Dtos.Pago.PagoMapper;
import com.vp.VentaProducto.Dtos.Pago.PagoToSaveDto;
import com.vp.VentaProducto.Entidades.Pago;
import com.vp.VentaProducto.Exception.PagoNotFoundException;
import com.vp.VentaProducto.Repositorios.PagoRepository;
import org.springframework.stereotype.Service;

@Service
public class PagoServiceIMPL implements PagoService{
    private final PagoRepository pagoRepository;

    public PagoServiceIMPL(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public PagoDto guardarPago(PagoToSaveDto pago) {
        Pago pago1= PagoMapper.INSTANCE.toEntity(pago);
        Pago pagoGuardado=pagoRepository.save(pago1);
        return PagoMapper.INSTANCE.PagoToDto(pagoGuardado);
    }

    @Override
    public PagoDto actualizarPago(PagoToSaveDto pago) {
        Pago pago1= PagoMapper.INSTANCE.toEntity(pago);
        Pago pagoExiste=pagoRepository.findById(pago.id()).orElseThrow(()->new PagoNotFoundException("Pago No Encontrado"));
        pagoExiste.setTotalPago(pago1.getTotalPago());
        pagoExiste.setMetodoDePago(pago1.getMetodoDePago());
        pagoExiste.setFechaPago(pago1.getFechaPago());
        pagoExiste.setPedido(pago1.getPedido());
        pagoExiste=pagoRepository.save(pagoExiste);
        return PagoMapper.INSTANCE.PagoToDto(pagoExiste);
    }

    @Override
    public PagoDto findById(Long id) {
        Pago pago=pagoRepository.findById(id).orElseThrow(()->new PagoNotFoundException("Pago No Encontrado"));
        return PagoMapper.INSTANCE.PagoToDto(pago);
    }

    @Override
    public void deleteById(Long id) {
        Pago pago=pagoRepository.findById(id).orElseThrow(()->new PagoNotFoundException("Pago No Encontrado"));
        pagoRepository.delete(pago);
    }
}
