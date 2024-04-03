package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioDto;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioMapper;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioMapperImpl;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioToSaveDTO;
import com.vp.VentaProducto.Dtos.Pedido.PedidoToSaveDto;
import com.vp.VentaProducto.Entidades.DetalleEnvio;
import com.vp.VentaProducto.Entidades.EstatusPedido;
import com.vp.VentaProducto.Entidades.Pedido;
import com.vp.VentaProducto.Exception.DetalleEnvioNotFoundException;
import com.vp.VentaProducto.Repositorios.DetalleEnvioRepository;
import com.vp.VentaProducto.Repositorios.PedidoRepository;
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
class DetalleEnvioServiceIMPLTest {

    @Mock
    private DetalleEnvioRepository detalleEnvioRepository;
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private DetalleEnvioServiceIMPL detalleEnvioService;
    @InjectMocks
    private PedidoServiceIMPL pedidoService;
    DetalleEnvio detalleEnvio;
    Pedido pedido;
    DetalleEnvioMapper detalleEnvioMapper;
    @BeforeEach
    void setUp(){
        pedidoService=new PedidoServiceIMPL(pedidoRepository);
        pedido=new Pedido();
        pedido.setId(1L);
        pedido.setPago(null);
        pedido.setItemPedidos(null);
        pedido.setDetalleEnvio(null);
        pedido.setEstado(EstatusPedido.PENDIENTE);
        pedido.setCliente(null);
        pedido.setFechaPedido(LocalDateTime.of(2020,11,12,12,12));
        pedidoRepository.save(pedido);

        detalleEnvioMapper=new DetalleEnvioMapperImpl();
        detalleEnvioService= new DetalleEnvioServiceIMPL(detalleEnvioRepository);
        detalleEnvio=new DetalleEnvio();
        detalleEnvio.setId(1L);
        detalleEnvio.setNumeroGuia(123444);
        detalleEnvio.setTransportadora("testtransportadora1");
        detalleEnvio.setDireccion("dereccion1test");
        detalleEnvio.setPedido(pedido);
        detalleEnvioRepository.save(detalleEnvio);
    }
    @Test
    void GiveDetalleEnvio_WhenDetalleEnvioCreate_ThenReturnDetalleEnvioGuardado() {
        pedido.setId(1L);
        pedido.setPago(null);
        pedido.setItemPedidos(null);
        pedido.setDetalleEnvio(null);
        pedido.setEstado(EstatusPedido.PENDIENTE);
        pedido.setCliente(null);
        pedido.setFechaPedido(LocalDateTime.of(2020,11,12,12,12));
        pedidoRepository.save(pedido);
        PedidoToSaveDto pedidpGuardado=new PedidoToSaveDto(
                1L,
                LocalDateTime.of(2020,11,12,12,12),
                EstatusPedido.PENDIENTE,
                null);
        pedidoService.guardarPedido(pedidpGuardado);
        detalleEnvio.setId(1L);
        detalleEnvio.setNumeroGuia(123444);
        detalleEnvio.setTransportadora("testtransportadora1");
        detalleEnvio.setDireccion("dereccion1test");
        detalleEnvio.setPedido(pedido);
        given(detalleEnvioRepository.save(any())).willReturn(detalleEnvio);
        //Given
        DetalleEnvioToSaveDTO detalleEnvioGuardado=new DetalleEnvioToSaveDTO(
                1L,
                "dereccion1test",
                "testtransportadora1",
                123444,
                null);
        //When
        DetalleEnvioDto detalleEnvioDto=detalleEnvioService.guardarDetalleEnvio(detalleEnvioGuardado);
        //Then
        assertThat(detalleEnvioDto).isNotNull();
        assertThat(detalleEnvioDto.id()).isEqualTo(1L);
    }

    @Test
    void GiveDetalleEnvioId_whenFindDetalleEnvioById_thenReturnDetalleEnvio() {
        Long detalleEnvioID=1L;
        //Given
        given(detalleEnvioRepository.findById(detalleEnvioID)).willReturn(Optional.of(detalleEnvio));
        //When
        DetalleEnvioDto detalleEnvioDto=detalleEnvioService.findById(detalleEnvioID);
        //Then
        assertThat(detalleEnvioDto).isNotNull();
    }

    @Test
    void GiveDetalleEnvioId_whenFindDetalleEnvioById_thenReturnDetalleEnvioException() {
        //Given
        given(detalleEnvioRepository.findById(any())).willReturn(Optional.empty());
        assertThrows(DetalleEnvioNotFoundException.class,()-> detalleEnvioService.findById(any()),"Detalle de Envio no Encontrao");
    }

    @Test
    void GivenDetalleEnvio_WhenDetalleEnvioUpdate_ThenDetalleEnvioUpdate() {
        detalleEnvio.setId(1L);
        detalleEnvio.setNumeroGuia(123444);
        detalleEnvio.setTransportadora("testtransportadora1");
        detalleEnvio.setDireccion("dereccion1test");
        detalleEnvio.setPedido(null);
        given(detalleEnvioRepository.save(any())).willReturn(detalleEnvio);
        //Given
        DetalleEnvioToSaveDTO detalleEnvioGuardado=new DetalleEnvioToSaveDTO(
                1L,
                "dereccion1test",
                "testtransportadora1",
                123444,
                null);
        detalleEnvioService.guardarDetalleEnvio(detalleEnvioGuardado);
        //When
        detalleEnvio.setNumeroGuia(1443212);
        DetalleEnvioDto detalleEnvioDto=detalleEnvioService.guardarDetalleEnvio(detalleEnvioGuardado);
        //Then
        assertThat(detalleEnvioDto).isNotNull();
        assertThat(detalleEnvioDto.id()).isEqualTo(1L);
    }

    @Test
    void GiveDetalleEnvioId_WhenDeleteByDetalleEnvioId_thenDetalleEnvioIsDeleted() {
        //Given
        given(detalleEnvioRepository.findById(1L)).willReturn(Optional.of(detalleEnvio));
        willDoNothing().given(detalleEnvioRepository).delete(detalleEnvio);
        //When
        detalleEnvioService.deleteById(1L);
        //Then
        verify(detalleEnvioRepository, times(1)).delete(detalleEnvio);
    }

    @Test
    void GiveDetalleEnvio_whenFindByPedidoId_thenReturnDetalleEnvio() {
        /*pedido.setId(1L);
        pedido.setPago(null);
        pedido.setItemPedidos(null);
        pedido.setDetalleEnvio(null);
        pedido.setEstado(EstatusPedido.PENDIENTE);
        pedido.setCliente(null);
        pedido.setFechaPedido(LocalDateTime.of(2020,11,12,12,12));
        pedidoRepository.save(pedido);
        PedidoToSaveDto pedidpGuardado=new PedidoToSaveDto(
                1L,
                LocalDateTime.of(2020,11,12,12,12),
                EstatusPedido.PENDIENTE,
                null);
        pedidoService.guardarPedido(pedidpGuardado);*/
        detalleEnvio.setId(1L);
        detalleEnvio.setNumeroGuia(123444);
        detalleEnvio.setTransportadora("testtransportadora1");
        detalleEnvio.setDireccion("dereccion1test");
        detalleEnvio.setPedido(new Pedido(1L,LocalDateTime.of(2020,11,12,12,12),EstatusPedido.PENDIENTE,null,null,null,null));
        given(detalleEnvioRepository.save(any())).willReturn(detalleEnvio);
        DetalleEnvioToSaveDTO detalleEnvioGuardado=new DetalleEnvioToSaveDTO(
                1L,
                "dereccion1test",
                "testtransportadora1",
                123444,
                null);
        detalleEnvioService.guardarDetalleEnvio(detalleEnvioGuardado);
        //Given
        given(detalleEnvioRepository.findByPedidoId(detalleEnvioGuardado.pedidoDto().id())).willReturn(Optional.of(detalleEnvio));
        //When
        Optional<DetalleEnvioDto> detalleEnvioDto=detalleEnvioService.findByPedidoId(1L);
        //Then
        assertThat(detalleEnvioDto).isNotNull();
    }

    @Test
    void GiveDetalleEnvio_whenFindByTransportadora_thenReturnDetalleEnvio() {
        String transportadora="testtransportadora1";
        //Given
        given(detalleEnvioRepository.findByTransportadora(transportadora)).willReturn(Optional.of(List.of(detalleEnvio)));
        //When
        Optional<List<DetalleEnvioDto>> detalleEnvioDto=detalleEnvioService.findByTransportadora(transportadora);
        //Then
        assertThat(detalleEnvioDto).isNotNull();
    }

    @Test
    void GiveDetalleEnvio_whenFindByEstadoPedido_thenReturnDetalleEnvio() {
        EstatusPedido estatusPedido=EstatusPedido.ENTREGADO;
        //Given
        given(detalleEnvioRepository.findByestado(estatusPedido)).willReturn(Optional.of(List.of(detalleEnvio)));
        //When
        Optional<List<DetalleEnvioDto>> detalleEnvioDto=detalleEnvioService.findByEstado(estatusPedido);
        //Then
        assertThat(detalleEnvioDto).isNotNull();
    }

    @Test
    void getDestalleEnvio() {
        Optional<List<DetalleEnvioDto>> detalleEnvioDto=detalleEnvioService.getDestalleEnvio();
        assertThat(detalleEnvioDto).isNotNull();
    }
}