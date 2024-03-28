package com.vp.VentaProducto.Repositorios;


import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


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
        Optional<Cliente> optionalCliente= clienteRepository.findById(clienteSave.getId());
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
    void GiveClienteId_WhenDeleteById_thenClienteIsDeleted(){
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

    @Test
    void GiveCliente_whenFindByEmail_thenReturnTheClienteIsFound(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("juan cuna");
        cliente.setEmail("juacho@gmail.com");
        cliente.setDireccion("Urbanizacion mayorca manzana #7 casa #200");
        cliente.setPedidos(null);
        clienteRepository.save(cliente);
        //when
        Optional<Cliente> clienteFound=clienteRepository.findByEmail("juacho@gmail.com");
        //then
        assertThat(clienteFound).isPresent();
    }

    @Test
    void GiveCliente_whenFindByDireccion_thenReturnListOfClient(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");
        clienteRepository.save(cliente);

        Cliente cliente1=new Cliente();
        cliente1.setNombre("robertos");
        cliente1.setDireccion("casa28");
        cliente1.setEmail("jolsaaagmail.com");
        clienteRepository.save(cliente1);
        //when
        Optional<List<Cliente>> foudnCliente=clienteRepository.findByDireccion("casa28");
        //then
        assertThat(foudnCliente).isPresent();
        assertThat(foudnCliente.get()).hasSize(2);
    }

    @Test
    void GiveCliente_whenFindByNombre_thenReturnListOfCliente(){
        //give
        Cliente cliente=new Cliente();
        cliente.setNombre("rober");
        cliente.setDireccion("casa28");
        cliente.setEmail("jolsagmail.com");
        clienteRepository.save(cliente);

        Cliente cliente1=new Cliente();
        cliente1.setNombre("robertos");
        cliente1.setDireccion("casa28");
        cliente1.setEmail("jolsaaagmail.com");
        clienteRepository.save(cliente1);
        //when
        Optional<List<Cliente>> foudnCliente=clienteRepository.findByNombreStartingWith("rober");
        //then
        assertThat(foudnCliente).isPresent();
        assertThat(foudnCliente.get()).hasSize(2);
    }
}