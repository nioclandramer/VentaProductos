package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoDto;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoMapper;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoMapperImpl;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoToSaveDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoDto;
import com.vp.VentaProducto.Entidades.EstatusPedido;
import com.vp.VentaProducto.Entidades.ItemPedido;
import com.vp.VentaProducto.Entidades.Pedido;
import com.vp.VentaProducto.Entidades.Producto;
import com.vp.VentaProducto.Exception.ItemPedidoNotFoundException;
import com.vp.VentaProducto.Repositorios.ItemPedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
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
        ItemPedidoToSaveDto itemPedidoGuardado=new ItemPedidoToSaveDto(
                1L,
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
        ItemPedidoToSaveDto itemPedidoGuardado=new ItemPedidoToSaveDto(
                1L,
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
    void GiveItemPedidoId_WhenDeleteByItemPedidoId_thenItemPedidoIsDeleted() {
        //Give
        given(itemPedidoRepository.findById(1L)).willReturn(Optional.of(itemPedido));
        willDoNothing().given(itemPedidoRepository).delete(itemPedido);
        //When
        itemPedidoService.deleteById(1L);
        //then
        verify(itemPedidoRepository,times(1)).delete(itemPedido);
    }

    @Test
    void GiveItemPedidoId_whenFindItemPedidoByPedidoId_thenReturnItemPedidofindBy() {
        itemPedido.setId(1L);
        itemPedido.setCantida(14);
        itemPedido.setPrecioUnitario(1234);
        itemPedido.setPedido(new Pedido(1L, LocalDateTime.of(2020,11,12,12,12), EstatusPedido.PENDIENTE,null,null,null,null));
        itemPedido.setProducto(null);
        given(itemPedidoRepository.save(any())).willReturn(itemPedido);
        ItemPedidoToSaveDto itemPedidoGuardado=new ItemPedidoToSaveDto(
                1L,
                14,
                1234,
                new PedidoDto(1L, LocalDateTime.of(2020,11,12,12,12), EstatusPedido.PENDIENTE,null,null,null,null),
                null);
        itemPedidoService.guardarItemPedido(itemPedidoGuardado);
        //Give
        given(itemPedidoRepository.findByPedidoId(itemPedidoGuardado.pedidoDto().id())).willReturn(Optional.of(List.of(itemPedido)));
        //When
        Optional<List<ItemPedidoDto>> itemPedidoDto=itemPedidoService.findByPedidoId(1L);
        //then
        assertThat(itemPedidoDto).isNotNull();
    }

    @Test
    void GiveItemPedidoId_whenFindItemPedidoByProductoId_thenReturnItemPedidofindBy() {
        itemPedido.setId(1L);
        itemPedido.setCantida(14);
        itemPedido.setPrecioUnitario(1234);
        itemPedido.setPedido(null);
        itemPedido.setProducto(new Producto(1L,"coco",12,11,null));
        given(itemPedidoRepository.save(any())).willReturn(itemPedido);
        ItemPedidoToSaveDto itemPedidoGuardado=new ItemPedidoToSaveDto(
                1L,
                14,
                1234,
                null,
                new ProductoDto(1L,"coco",12,11));
        itemPedidoService.guardarItemPedido(itemPedidoGuardado);
        //Give
        given(itemPedidoRepository.findByProductoId(itemPedidoGuardado.productoDto().id())).willReturn(Optional.of(List.of(itemPedido)));
        //When
        Optional<List<ItemPedidoDto>> itemPedidoDto=itemPedidoService.findByProductoId(1L);
        //then
        assertThat(itemPedidoDto).isNotNull();
    }

    @Test
    void totalVentasPorProducto() {
        //Optional<List<ItemPedido>> list=itemPedidoRepository.findByProductoId(1L);
        Long producto =1L;
        Integer totalEsperado=20;
        itemPedido.setId(1L);
        itemPedido.setCantida(2);
        itemPedido.setPrecioUnitario(10);
        itemPedido.setPedido(null);
        itemPedido.setProducto(new Producto(1L,"coco",2,10,null));
        given(itemPedidoRepository.totalVentasPorProducto(producto)).willReturn(Optional.of(totalEsperado));
        Optional<Integer> result= itemPedidoRepository.totalVentasPorProducto(producto);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(totalEsperado,result.get());
    }

    @Test
    void getItemPedido() {
        Optional<List<ItemPedidoDto>> itemPedidoDto=itemPedidoService.getItemPedido();
        assertThat(itemPedidoDto).isNotNull();
    }
}