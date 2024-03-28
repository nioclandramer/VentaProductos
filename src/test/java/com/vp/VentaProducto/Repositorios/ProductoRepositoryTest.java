package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import com.vp.VentaProducto.Entidades.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
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
    void GiveProducto_WhenCreate_ThenProductoIdIsSave(){
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
    void GiveProductoId_WhenFindById_ThenProductoIsFound(){
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
    void GiveProducto_WhenUpdate_ThenProductoUpdate(){
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
    void GiveProductoId_WhenDeleteById_ThenProductoIsDelete(){
        //Given
        Producto producto=new Producto();
        producto.setNombre("Crema de coc");
        producto.setStock(12);
        producto.setPrecio(15);
        Producto productoSave=productoRepository.save(producto);
        //When
        productoRepository.deleteById(productoSave.getId());
        //then
        assertThat(productoRepository.findById(productoSave.getId())).isEmpty();
    }

    @Test
    void findByNombreContainingIgnoreCase_WhenSave_ReturnsProductList(){
        //Given
        Producto producto1= new Producto();
        producto1.setNombre("Crema de Marihuana");
        producto1.setStock(15);
        producto1.setPrecio(12);

        Producto producto2= new Producto();
        producto2.setNombre("Postre de 4 leches");
        producto2.setStock(12);
        producto2.setPrecio(5);

        productoRepository.save(producto1);
        productoRepository.save(producto2);
        //When
        List<Producto> productosFound= productoRepository.findByNombreContainingIgnoreCase("leches");
        assertThat(productosFound.size()).isEqualTo(1);
        assertThat(productosFound).isNotEmpty();

    }
    @Test
    void findByProductsInStock_WhenFoundInStock_ThenResturnsList(){
        //Given
        Producto producto1= new Producto();
        producto1.setNombre("Crema de Marihuana");
        producto1.setStock(15);
        producto1.setPrecio(12);

        Producto producto2= new Producto();
        producto2.setNombre("Postre de 4 leches");
        producto2.setStock(12);
        producto2.setPrecio(5);

        productoRepository.save(producto1);
        productoRepository.save(producto2);
        //When
        Optional<List<Producto>> foundProducts= productoRepository.findByProductStockEqual(1);
        List<Producto> listPorductsFound =  foundProducts.get();
        //then
        assertThat(foundProducts).isPresent();
        assertThat(listPorductsFound).hasSize(2);


    }
    @Test
    void findProductsInDeterminatedStockAndPrice_WhenMatch_ThenReturnsAList(){
        //Given
        Producto producto1= new Producto();
        producto1.setNombre("Crema de Marihuana");
        producto1.setStock(15);
        producto1.setPrecio(12);

        Producto producto2= new Producto();
        producto2.setNombre("Postre de 4 leches");
        producto2.setStock(12);
        producto2.setPrecio(5);

        productoRepository.save(producto1);
        productoRepository.save(producto2);

        //When
        Optional<List<Producto>> foundProducts= productoRepository.findByPrecioAndStock(5,16);
        //then
        assertThat(foundProducts).isPresent();
        List<Producto> productoList= foundProducts.get();
        assertThat(productoList).hasSize(1);

    }
}