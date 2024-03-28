package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PedidoRepositoryTest extends AstractIntegrationBDTest {
    PedidoRepository pedidoRepository;
    ClienteRepository clienteRepository;
    ProductoRepository productoRepository;
    ItemPedidoRepository itemPedidoRepository;
    @Autowired
    public PedidoRepositoryTest(PedidoRepository pedidoRepository, ClienteRepository clienteRepository,ItemPedidoRepository itemPedidoRepository,ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository=clienteRepository;
        this.itemPedidoRepository=itemPedidoRepository;
        this.productoRepository=productoRepository;
    }

    @BeforeEach
    void setUp() {
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
        itemPedidoRepository.deleteAll();
        productoRepository.deleteAll();
    }

    @Test
    void GivePedido_WhenCreate_ThenPedidoIdIsSaved(){
        //Give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024,Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(cliente);
        //When
        Pedido pedidoSave = pedidoRepository.save(pedido);
        //then
        assertThat(pedidoSave).isNotNull();
    }

    @Test
    void GivePedidoId_whenFindById_thenPedidoIsFound(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024,Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(cliente);
        Pedido pedidoSave = pedidoRepository.save(pedido);
        //when
        Optional<Pedido> optionalPedido=pedidoRepository.findById(pedidoSave.getId());
        //then
        assertThat(optionalPedido).isPresent();
    }

    @Test
    void GivenPedido_WhenUpdate_ThenPedidoUpdated(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024,Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(cliente);
        Pedido pedidoSave = pedidoRepository.save(pedido);
        //When
        pedidoSave.setEstado(EstatusPedido.PENDIENTE);
        pedidoRepository.save(pedidoSave);
        //then
        assertThat(pedidoSave.getEstado()).isEqualTo(EstatusPedido.PENDIENTE);
    }

    @Test
    void GivePedidoId_WhenDeleteById_thenPedidoIsDeleted(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");

        Pedido pedido=new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024,Month.AUGUST,10,8,20));
        pedido.setEstado(EstatusPedido.ENVIADO);
        pedido.setCliente(cliente);
        Pedido pedidoSave = pedidoRepository.save(pedido);
        //when
        pedidoRepository.deleteById(pedidoSave.getId());
        //then
        assertThat(pedidoRepository.findById(pedidoSave.getId())).isEmpty();
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
    @Test
    void GivePedidoAndCliente_WhenFindClienteAndItemsPedidos_ReturnList(){
        //Given
        Producto producto= new Producto();
        producto.setNombre("Alpaca");
        producto.setStock(1);
        producto.setPrecio(20);
        productoRepository.save(producto);

        Cliente cliente= new Cliente();
        cliente.setDireccion("casa 30");
        cliente.setEmail("rafael.gmail.com");
        cliente.setNombre("orozo");
        clienteRepository.save(cliente);


        Pedido pedido= new Pedido();
        pedido.setFechaPedido(LocalDateTime.of(2024,Month.JULY,10,8,20));
        pedido.setEstado(EstatusPedido.ENTREGADO);
        pedido.setCliente(cliente);
        pedidoRepository.save(pedido);


        ItemPedido itemPedido= new ItemPedido();
        itemPedido.setPedido(pedido);
        itemPedido.setCantida(12);
        itemPedido.setProducto(producto);
        itemPedido.setPrecioUnitario(12);

        itemPedidoRepository.save(itemPedido);




        //When
        Optional<List<Pedido>> pedidosFound= pedidoRepository.findByClienteWhithItemPedidos(cliente);
        //Then
        assertThat(pedidosFound).isPresent();
        assertThat(pedidosFound.stream().toList()).hasSize(1);

        Pedido pedidos = pedidosFound.get().get(0);
        assertThat(pedidos).isNotNull();


    }



}