package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


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
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    void GivePago_WhenCreate_ThenPagoIdIsSaved(){
        //Give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");
        Cliente clienteSave=clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(clienteSave);
        Pedido pedidoSave = pedidoRepository.save(pedido);

        Pago pago=new Pago();
        pago.setTotalPago(123F);
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setFechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10));
        pago.setPedido(pedidoSave);
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
        Cliente clienteSave=clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(clienteSave);
        Pedido pedidoSave = pedidoRepository.save(pedido);

        Pago pago=new Pago();
        pago.setTotalPago(123F);
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setFechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10));
        pago.setPedido(pedidoSave);
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
        Cliente clienteSave=clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(clienteSave);
        Pedido pedidoSave = pedidoRepository.save(pedido);

        Pago pago=new Pago();
        pago.setTotalPago(123F);
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setFechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10));
        pago.setPedido(pedidoSave);
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
        Cliente clienteSave=clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(clienteSave);
        Pedido pedidoSave = pedidoRepository.save(pedido);

        Pago pago=new Pago();
        pago.setTotalPago(123F);
        pago.setMetodoDePago(MetodoDePago.EFECTIVO);
        pago.setFechaPago(LocalDateTime.of(2024,Month.JULY,20,15,10));
        pago.setPedido(pedidoSave);
        Pago pagoSave=pagoRepository.save(pago);
        //when
        pagoRepository.deleteById(pagoSave.getId());
        //then
        assertThat(pagoRepository.findById(pagoSave.getId())).isEmpty();
    }
    @Test
    void GiveDates_WhenSearchBetweenDates_ReturnAnList(){
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");
        clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.PENDIENTE);
        pedido.setCliente(cliente);
        pedidoRepository.save(pedido);

        Pedido pedido3=new Pedido();
        pedido3.setFechaPedido(LocalDateTime.of(2021, Month.AUGUST,10,8,20));
        pedido3.setEstado(EstatusPedido.PENDIENTE);
        pedido3.setCliente(cliente);
        pedidoRepository.save(pedido3);

        Pago pago= Pago.builder().fechaPago(LocalDateTime.of(2024,Month.JULY,10,4,30))
                .metodoDePago(MetodoDePago.EFECTIVO)
                .totalPago(12F)
                .pedido(pedido).build();
        pagoRepository.save(pago);

        Pago pago2= Pago.builder()
                .fechaPago(LocalDateTime.of(2024,Month.DECEMBER,23,10,39))
                .metodoDePago(MetodoDePago.NEQUI)
                .totalPago(12F)
                .pedido(pedido3).build();
        pagoRepository.save(pago2);
        //when

        Optional<List<Pago>>pagos=pagoRepository.findByFechaPagoBetween(LocalDateTime.of(2024,Month.JULY,5,1,1)
                ,LocalDateTime.of(2025,Month.JULY,13,4,30));
        //Then
        assertThat(pagos).isPresent();
        assertThat(pagos.get()).hasSize(2);
    }
    @Test
    void GivePago_WhenSearchByIdAndMetododePago_returnAnPago(){
        //Give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        clienteRepository.save(cliente);

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024, Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.PENDIENTE);
        pedido.setCliente(cliente);

        pedidoRepository.save(pedido);

        Pago pago= Pago.builder().fechaPago(LocalDateTime.of(2024,Month.JULY,10,4,30))
                .metodoDePago(MetodoDePago.EFECTIVO)
                .totalPago(12F)
                .pedido(pedido).build();
        pagoRepository.save(pago);
       //When
        Optional<Pago> pagoFound = pagoRepository.findByIdAndMetodoDePago(pago.getId(), MetodoDePago.EFECTIVO);
        //Then
        assertThat(pagoFound).isPresent();
        assertThat(pagoFound.get().getMetodoDePago()).isEqualTo(MetodoDePago.EFECTIVO);
    }
}