package com.vp.VentaProducto.Dtos.Pedido;

import com.vp.VentaProducto.Entidades.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {
    PedidoMapper INSTANCE= Mappers.getMapper(PedidoMapper.class);

    PedidoDto pedidoToDto(Pedido pedido);
    Pedido dtoToPedido(PedidoDto dto);

    Pedido ToEntity(PedidoToSaveDto pedidoTSDTO);
}
