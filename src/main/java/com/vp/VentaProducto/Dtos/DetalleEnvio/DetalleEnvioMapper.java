package com.vp.VentaProducto.Dtos.DetalleEnvio;

import com.vp.VentaProducto.Entidades.DetalleEnvio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DetalleEnvioMapper {
    DetalleEnvioMapper INSTANCE = Mappers.getMapper(DetalleEnvioMapper.class);
    DetalleEnvioDto detalleEnvioToDto(DetalleEnvio detalleEnvio);
    DetalleEnvio dtoToDetalleEnvio(DetalleEnvioDto detalleEnvioDto);
    DetalleEnvio toEntity(DetalleEnvioToSaveDTO detalleEnvioToSaveDTO);
}
