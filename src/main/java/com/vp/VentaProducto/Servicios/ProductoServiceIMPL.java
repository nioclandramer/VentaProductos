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
        productoExiste.setItemPedidos(producto1.getItemPedidos());
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
}
