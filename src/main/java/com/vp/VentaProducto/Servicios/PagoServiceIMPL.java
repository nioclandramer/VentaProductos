package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteMapper;
import com.vp.VentaProducto.Dtos.Pago.PagoDto;
import com.vp.VentaProducto.Dtos.Pago.PagoMapper;
import com.vp.VentaProducto.Dtos.Pago.PagoToSaveDto;
import com.vp.VentaProducto.Entidades.Cliente;
import com.vp.VentaProducto.Entidades.MetodoDePago;
import com.vp.VentaProducto.Entidades.Pago;
import com.vp.VentaProducto.Exception.ClienteNotFoundException;
import com.vp.VentaProducto.Exception.PagoNotFoundException;
import com.vp.VentaProducto.Repositorios.PagoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public PagoDto actualizarPago(Long id,PagoToSaveDto pago) {
        Pago pago1= PagoMapper.INSTANCE.toEntity(pago);
        Pago pagoExiste=pagoRepository.findById(id).orElseThrow(()->new PagoNotFoundException("Pago No Encontrado"));
        pagoExiste.setTotalPago(pago1.getTotalPago());
        pagoExiste.setMetodoDePago(pago1.getMetodoDePago());
        pagoExiste.setFechaPago(pago1.getFechaPago());
        pagoExiste.setPedido(pago1.getPedido());

        Pago pagoGuardado=pagoRepository.save(pagoExiste);

        return PagoMapper.INSTANCE.PagoToDto(pagoGuardado);
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

    @Override
    public Optional<List<PagoDto>> findByFechaPagoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        List<Pago> pago=pagoRepository.findByFechaPagoBetween(fechaInicio,fechaFinal).orElseThrow(()->new PagoNotFoundException("Pago No Encontrado"));
        List<PagoDto> pagoDto = pago.stream().map(PagoMapper.INSTANCE::PagoToDto).collect(Collectors.toList());
        return Optional.of(pagoDto);
    }

    @Override
    public Optional<PagoDto> findByIdAndMetodoDePago(Long Id, MetodoDePago metodoDePago) {
        Pago pago=pagoRepository.findByIdAndMetodoDePago(Id,metodoDePago).orElseThrow(()->new PagoNotFoundException("Pago No Encontrado"));
        return Optional.ofNullable(PagoMapper.INSTANCE.PagoToDto(pago));
    }

    @Override
    public Optional<List<PagoDto>> getPago() {
        List<Pago> pago=pagoRepository.findAll();
        List<PagoDto> pagoDto=pago.stream().map(PagoMapper.INSTANCE::PagoToDto).collect(Collectors.toList());
        return Optional.of(pagoDto);
    }
}
