package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.Cliente;
import com.vp.VentaProducto.Entidades.EstatusPedido;
import com.vp.VentaProducto.Entidades.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoRepositoryTest extends AstractIntegrationBDTest {
    PedidoRepository pedidoRepository;
    ClienteRepository clienteRepository;
    @Autowired
    public PedidoRepositoryTest(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository=clienteRepository;
    }

    @BeforeEach
    void setUp() {
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
    }
    @Test
    void PedidosWithDate_WhenSearchPedidosBeetwenDates_ThenListPedidos(){

        //Given
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");


        Pedido pedido1= new Pedido();
        pedido1.setFechaPedido(LocalDateTime.of(2024,Month.AUGUST,10,8,20));
        pedido1.setEstado(EstatusPedido.ENVIADO);
        pedido1.setCliente(cliente);

        Pedido pedido2= new Pedido();
        pedido2.setFechaPedido(LocalDateTime.of(2024,Month.JULY,10,8,20));
        pedido2.setEstado(EstatusPedido.ENTREGADO);
        pedido2.setCliente(cliente);

        Pedido pedido3= new Pedido();
        pedido3.setFechaPedido(LocalDateTime.of(2024,Month.FEBRUARY,10,8,20));
        pedido3.setEstado(EstatusPedido.ENVIADO);
        pedido3.setCliente(cliente);

        clienteRepository.save(cliente);
        pedidoRepository.save(pedido1);
        pedidoRepository.save(pedido2);
        pedidoRepository.save(pedido3);

        //When
        Optional<List<Pedido>> pedidosFoundInDate= pedidoRepository.findByFechaPedidoBetween(LocalDateTime
                .of(2024,Month.JULY,1,10,20)
                ,LocalDateTime.of(2024,Month.AUGUST,9,20,20));
        //Then
        assertThat(pedidosFoundInDate).isPresent();
        assertThat(pedidosFoundInDate.stream().toList()).hasSize(1);


    }

    @Test
    void GivenPedidosAndClient_WhenFoundPedidosWithEspecificClientAndStatus_ThenReturnList(){
        //Given
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolgmail.com");

        Cliente cliente2=new Cliente();
        cliente2.setNombre("rober");
        cliente2.setDireccion("casa28");
        cliente2.setEmail("jolsagmail.com");
        clienteRepository.save(cliente);
        clienteRepository.save(cliente2);


        Pedido pedido1= new Pedido();
        pedido1.setFechaPedido(LocalDateTime.of(2024,Month.AUGUST,10,8,20));
        pedido1.setEstado(EstatusPedido.ENVIADO);
        pedido1.setCliente(cliente);

        Pedido pedido2= new Pedido();
        pedido2.setFechaPedido(LocalDateTime.of(2024,Month.JULY,10,8,20));
        pedido2.setEstado(EstatusPedido.ENTREGADO);
        pedido2.setCliente(cliente2);
        pedidoRepository.save(pedido1);
        pedidoRepository.save(pedido2);

        //When
        Optional<List<Pedido>> pedidosFound=pedidoRepository.findByClienteAndEstado(cliente2.getId(),EstatusPedido.ENTREGADO);
        //Then
        assertThat(pedidosFound).isPresent();
        assertThat(pedidosFound.stream().toList()).hasSize(1);
        Pedido pedido3=pedidosFound.get().get(0);
        assertThat(pedido3.getEstado()).isEqualTo(EstatusPedido.ENTREGADO);
    }



}