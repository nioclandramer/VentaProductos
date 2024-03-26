package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

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
    void giveProducto_WhenCreate_ProductoIdIsSave(){
        //Given
        Producto producto=new Producto();
        producto.setNombre("Crema de Marihuana");
        producto.setStock(15);
        producto.setPrecio(12);
        //When
        Producto productoSave=productoRepository.save(producto);
        //then
        assertThat(productoSave).isNotNull();
        //assertThat(productoSave.getStock()).isEqualTo(15);

    }


}