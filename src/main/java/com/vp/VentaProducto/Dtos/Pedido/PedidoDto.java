package com.vp.VentaProducto.Dtos.Pedido;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioDto;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoDto;
import com.vp.VentaProducto.Dtos.Pago.PagoDto;
import com.vp.VentaProducto.Entidades.EstatusPedido;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public record PedidoDto(
        Long id,
        LocalDateTime fechaPedido,
        EstatusPedido estado,
        ClienteDto cliente,
        List<ItemPedidoDto> itemPedidoDtos,
        DetalleEnvioDto detalleEnvioDto,
        PagoDto pagoDto)  {
    public List<ItemPedidoDto> itemPedidoDtos(){
        return Collections.unmodifiableList(itemPedidoDtos);
    }

}
