package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;

class ProductoRepositoryTest extends AstractIntegrationBDTest {
    ProductoRepository productoRepository;
    @Autowired
    public ProductoRepositoryTest(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }
    @Test
    void giveProducto_WhenCreate_ThenProductoIdIsSave(){
        //Given
        Producto producto=new Producto();
        producto.setNombre("Crema de Marihuana");
        producto.setStock(15);
        producto.setPrecio(12);
        //When
        Producto productoSave=productoRepository.save(producto);
        //then
        assertThat(productoSave).isNotNull();
        assertThat(productoSave.getStock()).isEqualTo(15);
    }

    @Test
    void giveProductoId_WhenFindById_ThenProductoIsFound(){
        //Given
        Producto producto=new Producto();
        producto.setNombre("Crema de coc");
        producto.setStock(12);
        producto.setPrecio(15);
        Producto productoSave=productoRepository.save(producto);
        //When
        Optional<Producto> optionalProducto = productoRepository.findById(producto.getId());
        //then
        assertThat(optionalProducto).isPresent();
    }

    @Test
    void giveProducto_WhenUpdate_ThenProductoUpdate(){
        //Given
        Producto producto=new Producto();
        producto.setNombre("Crema de coc");
        producto.setStock(12);
        producto.setPrecio(15);
        Producto productoSave=productoRepository.save(producto);
        //When
        productoSave.setNombre("Crema de cocoo");
        productoRepository.save(productoSave);
        //then
        assertThat(productoSave.getNombre()).isEqualTo("Crema de cocoo");
    }

    @Test
    void giveProductoId_WhenDeleteById_ThenProductoIsDelete(){
        //Given
        Producto producto=new Producto();
        producto.setNombre("Crema de coc");
        producto.setStock(12);
        producto.setPrecio(15);
        Producto productoSave=productoRepository.save(producto);
        //When
        productoSave.setNombre("Crema de cocoo");
        productoRepository.save(productoSave);
        //then
        assertThat(productoSave.getNombre()).isEqualTo("Crema de cocoo");
    }
//1029301293810923801923801928309128309
}