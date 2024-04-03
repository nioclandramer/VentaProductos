package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Pago.PagoDto;
import com.vp.VentaProducto.Dtos.Pago.PagoMapper;
import com.vp.VentaProducto.Dtos.Pago.PagoMapperImpl;
import com.vp.VentaProducto.Dtos.Pago.PagoToSaveDto;
import com.vp.VentaProducto.Entidades.MetodoDePago;
import com.vp.VentaProducto.Entidades.Pago;
import com.vp.VentaProducto.Exception.PagoNotFoundException;
import com.vp.VentaProducto.Repositorios.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PagoServiceIMPLTest {
    @Mock
    private PagoRepository pagoRepository;
    @InjectMocks
    private PagoServiceIMPL pagoService;
    Pago pago;
    PagoMapper pagoMapper;
    @BeforeEach
    void setUp(){
        pagoMapper=new PagoMapperImpl();
        pagoService=new PagoServiceIMPL(pagoRepository);
        pago=new Pago();
        pago.setId(1L);
        pago.setTotalPago(1234F);
        pago.setFechaPago(LocalDateTime.of(2020,11,12,12,12));
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setPedido(null);
        pagoRepository.save(pago);
    }
    @Test
    void GivePago_WhenPagoCreate_ThenReturnPagoGuardado() {
        pago.setId(1L);
        pago.setTotalPago(1234F);
        pago.setFechaPago(LocalDateTime.of(2020,11,12,12,12));
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setPedido(null);
        given(pagoRepository.save(any())).willReturn(pago);
        //Give
        PagoToSaveDto pagoGuardado=new PagoToSaveDto(
                1L,
                1234F,
                LocalDateTime.of(2020,11,12,12,12),
                MetodoDePago.EFECTIVO,
                null);
        //When
        PagoDto pagoDto=pagoService.guardarPago(pagoGuardado);
        //then
        assertThat(pagoDto).isNotNull();
        assertThat(pagoDto.id()).isEqualTo(1L);
    }

    @Test
    void GivePago_WhenPagoFindById_ThenReturnPagoUpdate() {
        pago.setId(1L);
        pago.setTotalPago(1234F);
        pago.setFechaPago(LocalDateTime.of(2020,11,12,12,12));
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setPedido(null);
        given(pagoRepository.save(any())).willReturn(pago);
        //Give
        PagoToSaveDto pagoGuardado=new PagoToSaveDto(
                1L,
                1234F,
                LocalDateTime.of(2020,11,12,12,12),
                MetodoDePago.EFECTIVO,
                null);
        pagoService.guardarPago(pagoGuardado);
        //When
        pago.setTotalPago(1235F);
        PagoDto pagoDto=pagoService.guardarPago(pagoGuardado);
        //then
        assertThat(pagoDto).isNotNull();
        assertThat(pagoDto.totalPago()).isEqualTo(1235F);
    }

    @Test
    void GivePagoId_whenFindPagoById_thenReturnPago() {
        Long pagoId=1L;
        //Give
        given(pagoRepository.findById(pagoId)).willReturn(Optional.of(pago));
        //When
        PagoDto pagoDto=pagoService.findById(pagoId);
        //then
        assertThat(pagoDto).isNotNull();
    }

    @Test
    void GivePagoId_whenFindPagoById_thenReturnPagoNotFoundException() {
        //Give
        given(pagoRepository.findById(any())).willReturn(Optional.empty());
        assertThrows(PagoNotFoundException.class,()-> pagoService.findById(any()),"Pago No Encontrado");
    }

    @Test
    void GivePagoId_WhenDeleteByPagoId_thenPagoIsDeleted() {
        //Give
        given(pagoRepository.findById(1L)).willReturn(Optional.of(pago));
        willDoNothing().given(pagoRepository).delete(pago);
        //When
        pagoService.deleteById(1L);
        //then
        verify(pagoRepository,times(1)).delete(pago);
    }

    @Test
    void GivePagoId_whenFindPagoByFechaPagoBetween_thenReturnPago() {
        LocalDateTime fechaAntes=LocalDateTime.of(2020,7,11,12,12),fechaDespues=LocalDateTime.of(2021,3,22,12,12);
        //Give
        given(pagoRepository.findByFechaPagoBetween(fechaAntes,fechaDespues)).willReturn(Optional.of(List.of(pago)));
        //When
        Optional<List<PagoDto>> pagoDto=pagoService.findByFechaPagoBetween(fechaAntes,fechaDespues);
        //then
        assertThat(pagoDto).isNotNull();
    }

    @Test
    void GivePagoId_whenFindPagoByIdAndMetodoDePago_thenReturnPago() {
        Long pagoID=1L;
        MetodoDePago metodoDePago=MetodoDePago.EFECTIVO;
        //Give
        given(pagoRepository.findByIdAndMetodoDePago(pagoID,metodoDePago)).willReturn(Optional.of(pago));
        //When
        Optional<PagoDto> pagoDto=pagoService.findByIdAndMetodoDePago(pagoID,metodoDePago);
        //then
        assertThat(pagoDto).isNotNull();
    }

    @Test
    void getPago() {
        Optional<List<PagoDto>> pagoDto=pagoService.getPago();
        assertThat(pagoDto).isNotNull();
    }
}