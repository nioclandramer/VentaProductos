package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PagoRepositoryTest extends AstractIntegrationBDTest {
    PagoRepository pagoRepository;
    PedidoRepository pedidoRepository;
    ClienteRepository clienteRepository;
    @Autowired
    public PagoRepositoryTest(PagoRepository pagoRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pagoRepository = pagoRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @BeforeEach
    void setUp() {
        pagoRepository.deleteAll();
    }

    @Test
    void GivePago_WhenCreate_ThenPagoIdIsSaved(){
        //Give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(cliente);

        Pago pago=new Pago();
        pago.setTotalPago(123F);
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setFechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10));
        pago.setPedido(pedido);
        //When
        Pago pagoSave=pagoRepository.save(pago);
        //then
        assertThat(pagoSave).isNotNull();
        assertThat(pagoRepository.findById(pagoSave.getId())).isPresent();
    }

    @Test
    void GivePagoId_whenFindById_thenPagoIsFound(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(cliente);

        Pago pago=new Pago();
        pago.setTotalPago(123F);
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setFechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10));
        pago.setPedido(pedido);
        Pago pagoSave=pagoRepository.save(pago);
        //when
        Optional<Pago> optionalPago=pagoRepository.findById(pagoSave.getId());
        //then
        assertThat(optionalPago).isPresent();
    }

    @Test
    void GivenPago_WhenUpdate_ThenPagoUpdated(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(cliente);

        Pago pago=new Pago();
        pago.setTotalPago(123F);
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setFechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10));
        pago.setPedido(pedido);
        Pago pagoSave=pagoRepository.save(pago);
        //When
        pagoSave.setFechaPago(LocalDateTime.of(2024,Month.JULY,25,15,10));
        pagoRepository.save(pagoSave);
        //then
        assertThat(pagoSave.getFechaPago()).isEqualTo(LocalDateTime.of(2024,Month.JULY,25,15,10));
    }

    @Test
    void GiveId_WhenDeleteById_thenIsDeleted(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(cliente);

        Pago pago=new Pago();
        pago.setTotalPago(123F);
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setFechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10));
        pago.setPedido(pedido);
        Pago pagoSave=pagoRepository.save(pago);
        //when
        pagoRepository.deleteById(pagoSave.getId());
        //then
        assertThat(pagoRepository.findById(pagoSave.getId())).isEmpty();
    }
}