package com.vp.VentaProducto.Dtos.ItemPedido;

import com.vp.VentaProducto.Entidades.ItemPedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ItemPedidoMapper {
    ItemPedidoMapper INSTANCE = Mappers.getMapper(ItemPedidoMapper.class);
    @Mapping(source = "producto", target = "productoDto")
    ItemPedidoDto itemPedidoToDto(ItemPedido itemPedido);

    ItemPedido dtoToItemPedido(ItemPedidoDto itemPedidoDto);
    @Mapping(source = "productoDto", target = "producto")
    @Mapping(source = "pedidoDto", target = "pedido")
    ItemPedido toEntity(ItemPedidoToSaveDto itemPedidoToSaveDto);
}
