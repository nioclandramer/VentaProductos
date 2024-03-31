package com.vp.VentaProducto.Servicios;

import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioMapper;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoDto;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoMapper;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoToSaveDto;
import com.vp.VentaProducto.Entidades.DetalleEnvio;
import com.vp.VentaProducto.Entidades.ItemPedido;
import com.vp.VentaProducto.Exception.DetalleEnvioNotFoundException;
import com.vp.VentaProducto.Exception.ItemPedidoNotFoundException;
import com.vp.VentaProducto.Exception.ProductoNotFoundException;
import com.vp.VentaProducto.Repositorios.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemPedidoServiceIMPL implements ItemPedidoService{
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoServiceIMPL(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    @Override
    public ItemPedidoDto guardarItemPedido(ItemPedidoToSaveDto itemPedido) {
        ItemPedido itemPedido1= ItemPedidoMapper.INSTANCE.toEntity(itemPedido);
        ItemPedido itemPedidoGuardado=itemPedidoRepository.save(itemPedido1);
        return ItemPedidoMapper.INSTANCE.itemPedidoToDto(itemPedidoGuardado);
    }

    @Override
    public ItemPedidoDto actualizarItemPedido(ItemPedidoToSaveDto itemPedido) {
        ItemPedido itemPedido1= ItemPedidoMapper.INSTANCE.toEntity(itemPedido);
        ItemPedido itemPedidoExiste=itemPedidoRepository.findById(itemPedido.id()).orElseThrow(()-> new ItemPedidoNotFoundException("No Se Encontro El Item"));
        itemPedidoExiste.setProducto(itemPedido1.getProducto());
        itemPedidoExiste.setCantida(itemPedido1.getCantida());
        itemPedidoExiste.setPrecioUnitario(itemPedido1.getPrecioUnitario());
        itemPedidoExiste.setPedido(itemPedido1.getPedido());
        itemPedidoExiste=itemPedidoRepository.save(itemPedidoExiste);
        return ItemPedidoMapper.INSTANCE.itemPedidoToDto(itemPedidoExiste);
    }

    @Override
    public ItemPedidoDto findById(Long id) {
        ItemPedido itemPedido=itemPedidoRepository.findById(id).orElseThrow(()-> new ItemPedidoNotFoundException("No Se Encontro El Item"));
        return ItemPedidoMapper.INSTANCE.itemPedidoToDto(itemPedido);
    }

    @Override
    public void deleteById(Long id) {
        ItemPedido itemPedido=itemPedidoRepository.findById(id).orElseThrow(()-> new ItemPedidoNotFoundException("No Se Encontro El Item"));
        itemPedidoRepository.delete(itemPedido);
    }

    @Override
    public Optional<List<ItemPedidoDto>> findByPedidoId(Long pedidoId) {
       List<ItemPedido> itemPedido= itemPedidoRepository.findByPedidoId(pedidoId)
               .orElseThrow(()-> new ItemPedidoNotFoundException("No se encontró el pedido"));
        List<ItemPedidoDto> itemPedidoDtos=itemPedido.stream().map(ItemPedidoMapper.INSTANCE::itemPedidoToDto)
                .collect(Collectors.toList());
        return Optional.of(itemPedidoDtos);
    }

    @Override
    public Optional<List<ItemPedidoDto>> findByProductoId(Long productoId) {
        List<ItemPedido> itemPedidos= itemPedidoRepository.findByProductoId(productoId)
                .orElseThrow(()->new ProductoNotFoundException("No se encontró el producto"));
        List<ItemPedidoDto> itemPedidoDtos=itemPedidos.stream().map(ItemPedidoMapper.INSTANCE::itemPedidoToDto)
                .collect(Collectors.toList());
        return Optional.of(itemPedidoDtos);
    }

    @Override
    public Optional<Integer> totalVentasPorProducto(Long ProductoId) {
        return itemPedidoRepository.totalVentasPorProducto(ProductoId);
    }
}
