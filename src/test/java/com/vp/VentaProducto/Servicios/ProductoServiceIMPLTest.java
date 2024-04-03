package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Producto.ProductoDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoMapper;
import com.vp.VentaProducto.Dtos.Producto.ProductoMapperImpl;
import com.vp.VentaProducto.Dtos.Producto.ProductoToSaveDto;
import com.vp.VentaProducto.Entidades.Producto;
import com.vp.VentaProducto.Exception.ProductoNotFoundException;
import com.vp.VentaProducto.Repositorios.ProductoRepository;
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
class ProductoServiceIMPLTest {
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceIMPL productoService;

    Producto producto;
    ProductoMapper productoMapper;
    @BeforeEach
    void setUp(){
       productoMapper=new ProductoMapperImpl();
       productoService=new ProductoServiceIMPL(productoRepository);
       producto=new Producto();
       producto.setId(1L);
       producto.setNombre("test1coco");
       producto.setStock(14);
       producto.setPrecio(16);
       producto.setItemPedidos(null);
       productoRepository.save(producto);
    }
    @Test
    void GiveProducto_WhenProductoCreate_ThenReturnProductoGuardado() {
        producto.setId(1L);
        producto.setNombre("test1coco");
        producto.setStock(14);
        producto.setPrecio(16);
        producto.setItemPedidos(null);
        given(productoRepository.save(any())).willReturn(producto);
        //Given
        ProductoToSaveDto productoguardado=new ProductoToSaveDto(
                1L,
                "test1coco",
                14,
                16);
        //When
        ProductoDto productoDto=productoService.guardarProducto(productoguardado);
        //then
        assertThat(productoDto).isNotNull();
        assertThat(productoDto.id()).isEqualTo(1L);
    }

    @Test
    void GiveProductoId_whenFindProductoById_thenReturnProducto() {
        Long productoId=1L;
        //Given
        given(productoRepository.findById(productoId)).willReturn(Optional.of(producto));
        //When
        ProductoDto productoDto=productoService.findById(productoId);
        //then
        assertThat(productoDto).isNotNull();
    }

    @Test
    void GiveProductoId_whenFindProductoById_thenReturnException() {
        //Given
        given(productoRepository.findById(any())).willReturn(Optional.empty());
        //When
        assertThrows(ProductoNotFoundException.class,()->productoService.findById(any()),"ProductoNoEncontado");
    }

    @Test
    void GivenProducto_WhenProductoUpdate_ThenProductoUpdate() {
        producto.setId(1L);
        producto.setNombre("test1coco");
        producto.setStock(14);
        producto.setPrecio(16);
        producto.setItemPedidos(null);
        given(productoRepository.save(any())).willReturn(producto);
        //Given
        ProductoToSaveDto productoguardado=new ProductoToSaveDto(
                1L,
                "test1coco",
                14,
                16);
        productoService.guardarProducto(productoguardado);
        //When
        producto.setPrecio(17);
        ProductoDto productoDto=productoService.guardarProducto(productoguardado);
        //then
        assertThat(productoDto).isNotNull();
        assertThat(productoDto.precio()).isEqualTo(17);
    }

    @Test
    void GiveProductoId_WhenDeleteByProductoId_thenProductoIsDeleted() {
        //Given
        given(productoRepository.findById(1L)).willReturn(Optional.of(producto));
        willDoNothing().given(productoRepository).delete(producto);
        //When
        productoService.deleteById(1L);
        //then
        verify(productoRepository,times(1)).delete(producto);
    }

    @Test
    void GiveProductoId_whenFindProductoByNombreIgnoreCase_thenReturnProducto() {
        String nombre="test1coco";
        //Given
        given(productoRepository.findByNombreContainingIgnoreCase(nombre)).willReturn(Optional.of(List.of(producto)));
        //When
        Optional<List<ProductoDto>> productoDto=productoService.findByNombreContainingIgnoreCase(nombre);
        //then
        assertThat(productoDto).isNotNull();
    }

    @Test
    void GiveProducto_whenFindProductoByStockEqual_thenReturnProducto() {
        Integer productoStok=14;
        //Given
        given(productoRepository.findByProductStockEqual(productoStok)).willReturn(Optional.of(List.of(producto)));
        //When
        Optional<List<ProductoDto>> productoDto=productoService.findByProductStockEqual(productoStok);
        //then
        assertThat(productoDto).isNotNull();
    }

    @Test
    void GiveProducto_whenFindProductoByPrecioAndStock_thenReturnProducto() {
        Integer productoStok=14,productoPrecio=16;
        //Given
        given(productoRepository.findByPrecioAndStock(productoPrecio,productoStok)).willReturn(Optional.of(List.of(producto)));
        //When
        Optional<List<ProductoDto>> productoDto=productoService.findByPrecioAndStock(productoPrecio,productoStok);
        //then
        assertThat(productoDto).isNotNull();
    }

    @Test
    void getProducto() {
        Optional<List<ProductoDto>> productoDto=productoService.getProducto();
        assertThat(productoDto).isNotNull();
    }
}