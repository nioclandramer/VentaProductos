package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoToSaveDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoMapper;
import com.vp.VentaProducto.Dtos.Producto.ProductoToSaveDto;
import com.vp.VentaProducto.Entidades.Producto;
import com.vp.VentaProducto.Exception.ProductoNotFoundException;
import com.vp.VentaProducto.Repositorios.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceIMPL implements ProductoService{
    private final ProductoRepository productoRepository;

    public ProductoServiceIMPL(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ProductoDto guardarProducto(ProductoToSaveDto producto) {
        Producto producto1= ProductoMapper.INSTANCE.toEntity(producto);
        Producto productoGuardado=productoRepository.save(producto1);
        return ProductoMapper.INSTANCE.productoToDto(productoGuardado);
    }

    @Override
    public ProductoDto actualizarProducto(ProductoToSaveDto producto) {
        Producto producto1= ProductoMapper.INSTANCE.toEntity(producto);
        Producto productoExiste=productoRepository.findById(producto.id()).orElseThrow(()->new ProductoNotFoundException("Producto No Encontrado"));
        productoExiste.setNombre(producto1.getNombre());
        productoExiste.setPrecio(producto1.getPrecio());
        productoExiste.setStock(producto1.getStock());
        productoExiste=productoRepository.save(productoExiste);
        return ProductoMapper.INSTANCE.productoToDto(productoExiste);
    }

    @Override
    public ProductoDto findById(Long id) {
        Producto producto=productoRepository.findById(id).orElseThrow(()->new ProductoNotFoundException("Producto No Encontrado"));
        return ProductoMapper.INSTANCE.productoToDto(producto);
    }

    @Override
    public void deleteById(Long id) {
        Producto producto=productoRepository.findById(id).orElseThrow(()->new ProductoNotFoundException("Producto No Encontrado"));
        productoRepository.delete(producto);
    }

    @Override
    public Optional<List<ProductoDto>> findByNombreContainingIgnoreCase(String termino) {
        List<Producto> productos= productoRepository.findByNombreContainingIgnoreCase(termino)
                .orElseThrow(()-> new ProductoNotFoundException("Producto No encontrado"));
        List<ProductoDto>productoDtos=productos.stream().map(ProductoMapper.INSTANCE::productoToDto)
                .collect(Collectors.toList());
        return Optional.of(productoDtos);
    }

    @Override
    public Optional<List<ProductoDto>> findByProductStockEqual(Integer num) {
        List<Producto> productos= productoRepository.findByProductStockEqual(num)
                .orElseThrow(()-> new ProductoNotFoundException("Producto No encontrado"));
        List<ProductoDto>productoDtos=productos.stream().map(ProductoMapper.INSTANCE::productoToDto)
                .collect(Collectors.toList());
        return Optional.of(productoDtos);
    }

    @Override
    public Optional<List<ProductoDto>> findByPrecioAndStock(Integer precio, Integer stock) {
        List<Producto> productos= productoRepository.findByPrecioAndStock(precio,stock)
                .orElseThrow(()-> new ProductoNotFoundException("Producto No encontrado"));
        List<ProductoDto>productoDtos=productos.stream().map(ProductoMapper.INSTANCE::productoToDto)
                .collect(Collectors.toList());
        return Optional.of(productoDtos);
    }

    @Override
    public Optional<List<ProductoDto>> getProducto() {
        List<Producto> producto=productoRepository.findAll();
        List<ProductoDto> productoDto=producto.stream().map(ProductoMapper.INSTANCE::productoToDto).collect(Collectors.toList());
        return Optional.of(productoDto);
    }
}
