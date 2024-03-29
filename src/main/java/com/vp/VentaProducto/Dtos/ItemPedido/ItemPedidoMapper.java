package com.vp.VentaProducto.Dtos.ItemPedido;

import com.vp.VentaProducto.Entidades.ItemPedido;
import org.mapstruct.factory.Mappers;

public interface ItemPedidoMapper {
    ItemPedidoMapper INSTANCE = Mappers.getMapper(ItemPedidoMapper.class);
    ItemPedidoDto itemPedidoToDto(ItemPedido itemPedido);
    ItemPedido dtoToItemPedido(ItemPedidoDto itemPedidoDto);
    ItemPedido toEntity(ItemPedidoMapper itemPedidoMapper);
}
