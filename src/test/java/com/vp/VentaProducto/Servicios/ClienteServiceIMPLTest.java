package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteToSaveDto;
import com.vp.VentaProducto.Entidades.Cliente;
import com.vp.VentaProducto.Exception.ClienteNotFoundException;
import com.vp.VentaProducto.Repositorios.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ClienteServiceIMPLTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceIMPL clienteService;
    Cliente cliente;
    @BeforeEach
    void setUp(){
        cliente=new Cliente();
        cliente.setId(1L);
        cliente.setNombre("test1");
        cliente.setEmail("test1@gmail");
        cliente.setDireccion("test1direccion");
        cliente.setPedidos(null);
        clienteRepository.save(cliente);
    }

    @Test
    void GiveCliente_WhenClienteCreate_ThenReturnClienteGuardado(){
        cliente.setId(1L);
        cliente.setNombre("test1");
        cliente.setEmail("test1@gmail");
        cliente.setDireccion("test1direccion");
        cliente.setPedidos(null);
        given(clienteRepository.save(any())).willReturn(cliente);
        //Given

        ClienteToSaveDto clienteGuardado=new ClienteToSaveDto(
                1L,
                "test1",
                "test1@gmail",
                "test1direccion");
        //When
        ClienteDto clienteDto=clienteService.guardarCliente(clienteGuardado);
        //then
        assertThat(clienteDto).isNotNull();
        assertThat(clienteDto.id()).isEqualTo(1L);
    }

    @Test
    void GiveClienteId_whenFindClienteById_thenReturnCliente(){
        Long clienteID=1L;
        //given
        given(clienteRepository.findById(clienteID)).willReturn(Optional.ofNullable(cliente));
        //when
        ClienteDto clienteDto=clienteService.findById(clienteID);
        //then
        assertThat(clienteDto).isNotNull();
    }

    @Test
    void GiveClienteId_whenFindClienteById_thenReturnException(){
        //given
        given(clienteRepository.findById(any())).willReturn(Optional.ofNullable(null));
        assertThrows(ClienteNotFoundException.class,()->{
            clienteService.findById(any());
        },"Cliente No Encontrado");
    }

    @Test
    void GivenCliente_WhenClienteUpdate_ThenClienteUpdate(){
        cliente.setId(1L);
        cliente.setNombre("test1");
        cliente.setEmail("test1@gmail");
        cliente.setDireccion("test1direccion");
        cliente.setPedidos(null);
        given(clienteRepository.save(any())).willReturn(cliente);
        //Given

        ClienteToSaveDto clienteGuardado=new ClienteToSaveDto(
                1L,
                "test1",
                "test1@gmail",
                "test1direccion");
        ClienteDto clienteDto=clienteService.guardarCliente(clienteGuardado);
        //when
        cliente.setNombre("test2");
        clienteDto=clienteService.guardarCliente(clienteGuardado);
        //then
        assertThat(clienteDto).isNotNull();
        assertThat(clienteDto.id()).isEqualTo(1L);
    }

    @Test
    void GiveClienteId_WhenDeleteByClienteId_thenClienteIsDeleted(){
        given(clienteRepository.findById(1L)).willReturn(Optional.of(cliente));
        willDoNothing().given(clienteRepository).delete(cliente);
        clienteService.deleteByID(1L);
        verify(clienteRepository, times(1)).delete(cliente);
    }
}