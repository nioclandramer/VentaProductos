package com.vp.VentaProducto.Repositorios;


import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryTest extends AstractIntegrationBDTest {
    ClienteRepository clienteRepository;

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
        //When
        Cliente clienteSave=clienteRepository.save(cliente);
        //then
        assertThat(clienteSave).isNotNull();
        assertThat(clienteSave.getNombre()).isEqualTo("juan cuna");
    }

    @Test
    void GiveClienteId_whenFindById_thenIsFound(){
        //give
        //when
        //then
    }


}