package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoDto;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoMapper;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoMapperImpl;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoToSaveDto;
import com.vp.VentaProducto.Entidades.ItemPedido;
import com.vp.VentaProducto.Exception.ItemPedidoNotFoundException;
import com.vp.VentaProducto.Repositorios.ItemPedidoRepository;
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
@ExtendWith(MockitoExtension.class)
class ItemPedidoServiceIMPLTest {
    @Mock
    private ItemPedidoRepository itemPedidoRepository;
    @InjectMocks
    private ItemPedidoServiceIMPL itemPedidoService;
    ItemPedido itemPedido;
    ItemPedidoMapper itemPedidoMapper;
    @BeforeEach
    void setUp(){
        itemPedidoMapper=new ItemPedidoMapperImpl();
        itemPedidoService=new ItemPedidoServiceIMPL(itemPedidoRepository);
        itemPedido=new ItemPedido();
        itemPedido.setId(1L);
        itemPedido.setCantida(14);
        itemPedido.setPrecioUnitario(1234);
        itemPedido.setPedido(null);
        itemPedido.setProducto(null);
        itemPedidoRepository.save(itemPedido);
    }
    @Test
    void GiveItemPedid_WhenItemPedidCreate_ThenReturnPagoGuardadoItemPedido() {
        itemPedido.setId(1L);
        itemPedido.setCantida(14);
        itemPedido.setPrecioUnitario(1234);
        itemPedido.setPedido(null);
        itemPedido.setProducto(null);
        given(itemPedidoRepository.save(any())).willReturn(itemPedido);
        //Give
        ItemPedidoToSaveDto itemPedidoGuardado=new ItemPedidoToSaveDto(1L,
                14,
                1234,
                null,
                null);
        //When
        ItemPedidoDto itemPedidoDto=itemPedidoService.guardarItemPedido(itemPedidoGuardado);
        //then
        assertThat(itemPedidoDto).isNotNull();
        assertThat(itemPedidoDto.id()).isEqualTo(1L);
    }

    @Test
    void GivePago_WhenPagoFindById_ThenReturnItemPedidoUpdate() {
        itemPedido.setId(1L);
        itemPedido.setCantida(14);
        itemPedido.setPrecioUnitario(1234);
        itemPedido.setPedido(null);
        itemPedido.setProducto(null);
        given(itemPedidoRepository.save(any())).willReturn(itemPedido);
        //Give
        ItemPedidoToSaveDto itemPedidoGuardado=new ItemPedidoToSaveDto(1L,
                14,
                1234,
                null,
                null);
        itemPedidoService.guardarItemPedido(itemPedidoGuardado);
        //When
        itemPedido.setCantida(15);
        ItemPedidoDto itemPedidoDto=itemPedidoService.guardarItemPedido(itemPedidoGuardado);
        //then
        assertThat(itemPedidoDto).isNotNull();
        assertThat(itemPedidoDto.cantida()).isEqualTo(15);
    }

    @Test
    void GiveItemPedidoId_whenFindItemPedidoById_thenReturnItemPedido() {
        Long itemPedidoID=1L;
        //Give
        given(itemPedidoRepository.findById(itemPedidoID)).willReturn(Optional.of(itemPedido));
        //When
        ItemPedidoDto itemPedidoDto=itemPedidoService.findById(itemPedidoID);
        //then
        assertThat(itemPedidoDto).isNotNull();
    }

    @Test
    void GiveItemPedidoId_whenFindItemPedidoById_thenReturnItemPedidoNotFoundException() {
        //Give
        given(itemPedidoRepository.findById(any())).willReturn(Optional.empty());
        assertThrows(ItemPedidoNotFoundException.class,()-> itemPedidoService.findById(any()),"No Existe El ItemPedido");
    }

    @Test
    void deleteById() {
        //Give

        //When

        //then
    }

    @Test
    void findByPedidoId() {
        //Give

        //When

        //then
    }

    @Test
    void findByProductoId() {
        //Give

        //When

        //then
    }

    @Test
    void totalVentasPorProducto() {
        //Give

        //When

        //then
    }

    @Test
    void getItemPedido() {
        //Give

        //When

        //then
    }
}