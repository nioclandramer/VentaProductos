package com.vp.VentaProducto.Dtos.Pago;

import com.vp.VentaProducto.Entidades.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PagoMapper {
    PagoMapper INSTANCE= Mappers.getMapper(PagoMapper.class);

    PagoDto PagoToDto(Pago pago);
    Pago dtoToPago(PagoDto dto);
    Pago toEntity(PagoToSaveDto pagoSDTO);
}
