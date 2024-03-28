package com.vp.VentaProducto.Repositorios;


import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryTest extends AstractIntegrationBDTest {
    ClienteRepository clienteRepository;
    @Autowired
    public ClienteRepositoryTest(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;
    }

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    void GiveCliente_WhenCreate_ThenClienteIdIsSaved(){
        //Give
        Cliente cliente=new Cliente();
        cliente.setNombre("juan cuna");
        cliente.setEmail("juacho@gmail.com");
        cliente.setDireccion("Urbanizacion mayorca manzana #7 casa #200");
        cliente.setPedidos(null);
        //When
        Cliente clienteSave=clienteRepository.save(cliente);
        //then
        assertThat(clienteSave).isNotNull();
        assertThat(clienteSave.getNombre()).isEqualTo("juan cuna");
    }

    @Test
    void GiveClienteId_whenFindById_thenClienteIsFound(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("juan cuna");
        cliente.setEmail("juacho@gmail.com");
        cliente.setDireccion("Urbanizacion mayorca manzana #7 casa #200");
        cliente.setPedidos(null);
        Cliente clienteSave=clienteRepository.save(cliente);
        //when
        Optional<Cliente> optionalCliente= clienteRepository.findById(cliente.getId());
        //then
        assertThat(optionalCliente).isPresent();
    }

    @Test
    void GivenCliente_WhenUpdate_ThenClienteUpdated(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("juan cuna");
        cliente.setEmail("juacho@gmail.com");
        cliente.setDireccion("Urbanizacion mayorca manzana #7 casa #200");
        cliente.setPedidos(null);
        Cliente clienteSave=clienteRepository.save(cliente);
        //When
        clienteSave.setNombre("juan mesa");
        clienteRepository.save(clienteSave);
        //then
        assertThat(clienteSave.getNombre()).isEqualTo("juan mesa");
    }

    @Test
    void giveClienteId_WhenDeleteById_thenClienteIsDeleted(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("juan cuna");
        cliente.setEmail("juacho@gmail.com");
        cliente.setDireccion("Urbanizacion mayorca manzana #7 casa #200");
        cliente.setPedidos(null);
        Cliente clienteSave=clienteRepository.save(cliente);
        //when
        clienteRepository.deleteById(clienteSave.getId());
        //then
        assertThat(clienteRepository.findById(clienteSave.getId())).isEmpty();
    }

}