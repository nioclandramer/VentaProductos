package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.Cliente;
import com.vp.VentaProducto.Entidades.DetalleEnvio;
import com.vp.VentaProducto.Entidades.EstatusPedido;
import com.vp.VentaProducto.Entidades.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class DetalleEnvioRepositoryTest extends AstractIntegrationBDTest {
    DetalleEnvioRepository detalleEnvioRepository;
    ClienteRepository clienteRepository;
    PedidoRepository pedidoRepository;
    @Autowired
    public DetalleEnvioRepositoryTest(DetalleEnvioRepository detalleEnvioRepository, ClienteRepository clienteRepository, PedidoRepository pedidoRepository) {
        this.detalleEnvioRepository = detalleEnvioRepository;
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @BeforeEach
    void setUp() {
        detalleEnvioRepository.deleteAll();
        clienteRepository.deleteAll();
        pedidoRepository.deleteAll();
    }

    @Test
    void Give_WhenCreate_ThenIdIsSaved(){
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

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(cliente.getDireccion())
                .transportadora("SaramambicheTransportes")
                .numeroGuia(102938483)
                .pedido(pedidoSave)
                .build();
        //When
        DetalleEnvio detalleEnvioSave=detalleEnvioRepository.save(detalleEnvio);
        //then
        assertThat(detalleEnvioSave).isNotNull();
        assertThat(detalleEnvioRepository.findById(detalleEnvioSave.getId())).isPresent();
    }

    @Test
    void GiveId_whenFindById_thenIsFound(){
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

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(cliente.getDireccion())
                .transportadora("SaramambicheTransportes")
                .numeroGuia(102938483)
                .pedido(pedidoSave)
                .build();
        DetalleEnvio detalleEnvioSave=detalleEnvioRepository.save(detalleEnvio);
        //when
        Optional<DetalleEnvio> optionalDetalleEnvio=detalleEnvioRepository.findById(detalleEnvioSave.getId());
        //then
        assertThat(optionalDetalleEnvio).isPresent();
    }

    @Test
    void Given_WhenUpdate_ThenUpdated(){
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

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(cliente.getDireccion())
                .transportadora("SaramambicheTransportes")
                .numeroGuia(102938483)
                .pedido(pedidoSave)
                .build();
        DetalleEnvio detalleEnvioSave=detalleEnvioRepository.save(detalleEnvio);
        //When
        detalleEnvioSave.setNumeroGuia(1000087313);
        detalleEnvioRepository.save(detalleEnvio);
        //then
        assertThat(detalleEnvioSave.getNumeroGuia()).isEqualTo(1000087313);
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

        DetalleEnvio detalleEnvio=DetalleEnvio.builder()
                .direccion(cliente.getDireccion())
                .transportadora("SaramambicheTransportes")
                .numeroGuia(102938483)
                .pedido(pedidoSave)
                .build();
        DetalleEnvio detalleEnvioSave=detalleEnvioRepository.save(detalleEnvio);
        //when
        detalleEnvioRepository.deleteById(detalleEnvioSave.getId());
        //then
        assertThat(detalleEnvioRepository.findById(detalleEnvioSave.getId())).isEmpty();

    }
}