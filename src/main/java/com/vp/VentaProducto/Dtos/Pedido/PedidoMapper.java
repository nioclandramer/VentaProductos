package com.vp.VentaProducto.Dtos.Pedido;

import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoDto;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoMapper;
import com.vp.VentaProducto.Entidades.ItemPedido;
import com.vp.VentaProducto.Entidades.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PedidoMapper {
    PedidoMapper INSTANCE= Mappers.getMapper(PedidoMapper.class);
    @Mapping(source = "cliente", target = "cliente")
    @Mapping(source = "itemPedidos", target = "itemPedidoDtos")
    @Mapping(source = "detalleEnvio", target = "detalleEnvioDto")
    @Mapping(source = "pago", target = "pagoDto")

    PedidoDto pedidoToDto(Pedido pedido);
    Pedido dtoToPedido(PedidoDto dto);

    @Mapping(source = "cliente", target = "cliente")
    Pedido ToEntity(PedidoToSaveDto pedidoTSDTO);


}
