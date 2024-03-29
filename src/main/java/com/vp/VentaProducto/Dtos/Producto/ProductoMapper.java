package com.vp.VentaProducto.Dtos.Producto;

import com.vp.VentaProducto.Entidades.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductoMapper {
    ProductoMapper INSTANCE= Mappers.getMapper(ProductoMapper.class);

    ProductoDto productoToDto(Producto producto);
    Producto dtoToProducto(ProductoDto dto);

    Producto ToEntity(ProductoToSaveDto productoTSDTO);
}
