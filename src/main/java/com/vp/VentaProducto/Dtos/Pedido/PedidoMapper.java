package com.vp.VentaProducto.Dtos.Pedido;

import com.vp.VentaProducto.Entidades.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {
    PedidoMapper INSTANCE= Mappers.getMapper(PedidoMapper.class);
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "itemPedidos", target = "itemPedidoDtos")
    @Mapping(source = "detalleEnvio", target = "detalleEnvioDto")
    @Mapping(source = "pago", target = "pagoDto")
    PedidoDto pedidoToDto(Pedido pedido);
    Pedido dtoToPedido(PedidoDto dto);

    Pedido ToEntity(PedidoToSaveDto pedidoTSDTO);
}
