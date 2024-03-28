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

class ItemPedidoRepositoryTest extends AstractIntegrationBDTest {
    ItemPedidoRepository itemPedidoRepository;
    PedidoRepository pedidoRepository;
    ClienteRepository clienteRepository;
    ProductoRepository productoRepository;
    @Autowired
    public ItemPedidoRepositoryTest(ItemPedidoRepository itemPedidoRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @BeforeEach
    void setUp() {
        itemPedidoRepository.deleteAll();
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
        productoRepository.deleteAll();
    }

    @Test
    void GiveItemPedido_WhenCreate_ThenItemPedidoIdIsSaved(){
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

        Producto producto=new Producto();
        producto.setNombre("Crema de coc");
        producto.setStock(12);
        producto.setPrecio(15);
        Producto productoSave=productoRepository.save(producto);

        ItemPedido itemPedido=ItemPedido.builder()
                .cantida(200)
                .precioUnitario(400000)
                .pedido(pedidoSave)
                .producto(productoSave)
                .build();
        //When
        ItemPedido itemPedidoSave=itemPedidoRepository.save(itemPedido);
        //then
        assertThat(itemPedidoSave).isNotNull();
        assertThat(itemPedidoRepository.findById(itemPedidoSave.getId())).isPresent();
    }

    @Test
    void GiveItemPedidoId_whenFindById_thenItemPedidoIsFound(){
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

        Producto producto=new Producto();
        producto.setNombre("Crema de coc");
        producto.setStock(12);
        producto.setPrecio(15);
        Producto productoSave=productoRepository.save(producto);

        ItemPedido itemPedido=ItemPedido.builder()
                .cantida(200)
                .precioUnitario(400000)
                .pedido(pedidoSave)
                .producto(productoSave)
                .build();
        ItemPedido itemPedidoSave=itemPedidoRepository.save(itemPedido);
        //when
        Optional<ItemPedido> optionalItemPedido=itemPedidoRepository.findById(itemPedidoSave.getId());
        //then
        assertThat(optionalItemPedido).isPresent();
    }

    @Test
    void GivenItemPedido_WhenUpdate_ThenItemPedidoUpdated(){
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

        Producto producto=new Producto();
        producto.setNombre("Crema de coc");
        producto.setStock(12);
        producto.setPrecio(15);
        Producto productoSave=productoRepository.save(producto);

        ItemPedido itemPedido=ItemPedido.builder()
                .cantida(200)
                .precioUnitario(400000)
                .pedido(pedidoSave)
                .producto(productoSave)
                .build();
        ItemPedido itemPedidoSave=itemPedidoRepository.save(itemPedido);
        //When
        itemPedidoSave.setCantida(190);
        itemPedidoRepository.save(itemPedidoSave);
        //then
        assertThat(itemPedidoSave.getCantida()).isEqualTo(190);
    }

    @Test
    void GiveItemPedidoId_WhenDeleteById_thenItemPedidoIsDeleted(){
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

        Producto producto=new Producto();
        producto.setNombre("Crema de coc");
        producto.setStock(12);
        producto.setPrecio(15);
        Producto productoSave=productoRepository.save(producto);

        ItemPedido itemPedido=ItemPedido.builder()
                .cantida(200)
                .precioUnitario(400000)
                .pedido(pedidoSave)
                .producto(productoSave)
                .build();
        ItemPedido itemPedidoSave=itemPedidoRepository.save(itemPedido);
        //when
        itemPedidoRepository.deleteById(itemPedidoSave.getId());
        //then
        assertThat(itemPedidoRepository.findById(itemPedidoSave.getId())).isEmpty();
    }
}