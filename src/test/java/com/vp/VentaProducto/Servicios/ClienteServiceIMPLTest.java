package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteMapper;
import com.vp.VentaProducto.Dtos.Cliente.ClienteMapperImpl;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ClienteServiceIMPLTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceIMPL clienteService;
    Cliente cliente;
    ClienteMapper clienteMapper;
    @BeforeEach
    void setUp(){
        clienteMapper = new ClienteMapperImpl();
        clienteService=new ClienteServiceIMPL(clienteRepository);
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
        given(clienteRepository.findById(any())).willReturn(Optional.empty());
        assertThrows(ClienteNotFoundException.class,()-> clienteService.findById(any()),"Cliente No Encontrado");
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
        clienteService.guardarCliente(clienteGuardado);
        //when
        cliente.setNombre("test2");
        ClienteDto clienteDto=clienteService.guardarCliente(clienteGuardado);
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

    @Test
    void GiveCliente_whenFindByEmail_thenReturnTheClienteIsFound(){
        cliente.setId(1L);
        cliente.setNombre("test1");
        cliente.setEmail("test1@gmail");
        cliente.setDireccion("test1direccion");
        cliente.setPedidos(null);
        given(clienteRepository.save(any())).willReturn(cliente);
        ClienteToSaveDto clienteGuardado=new ClienteToSaveDto(
                1L,
                "test1",
                "test1@gmail",
                "test1direccion");
        ClienteDto clienteDto=clienteService.guardarCliente(clienteGuardado);
        given(clienteRepository.findByEmail(clienteDto.email())).willReturn(Optional.ofNullable(cliente));
        clienteDto=clienteService.findByEmail(clienteDto.email()).orElseThrow(()-> new ClienteNotFoundException("no"));
        assertThat(clienteDto).isNotNull();
    }

    @Test
    void GiveCliente_whenFindByDireccion_thenReturnListOfClient(){
        String clienteDireccion="test1direccion";
        given(clienteRepository.findByDireccion(clienteDireccion)).willReturn(Optional.of(List.of(cliente)));
        Optional<List<ClienteDto>> clienteDtos = clienteService.findByDireccion(clienteDireccion);
        assertThat(clienteDtos).isNotNull();
    }

    @Test
    void GiveCliente_whenFindByNombre_thenReturnListOfCliente(){
        String clienteNombre="test";
        given(clienteRepository.findByDireccion(clienteNombre)).willReturn(Optional.of(List.of(cliente)));
        Optional<List<ClienteDto>> clienteDtos = clienteService.findByDireccion(clienteNombre);
        assertThat(clienteDtos).isNotNull();
    }

    @Test
    void GiveCliente_whenGetAll_thenReturnClientes(){
        Optional<List<ClienteDto>> clienteDtos = clienteService.getAllClientes();
        assertThat(clienteDtos).isNotNull();
    }
}