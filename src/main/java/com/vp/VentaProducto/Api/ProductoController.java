package com.vp.VentaProducto.Api;

import com.vp.VentaProducto.Dtos.Producto.ProductoDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoToSaveDto;
import com.vp.VentaProducto.Servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final ProductoService productoService;
    @Autowired
    public ProductoController(ProductoService productoService){
        this.productoService=productoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductoDto>> getProductoByID(@PathVariable Long id){
      Optional<ProductoDto> productoDto= Optional.ofNullable(productoService.findById(id));
      return ResponseEntity.ok(productoDto);
    }
    @GetMapping("/searchT")
    public ResponseEntity<Optional<List<ProductoDto>>>searchProductos(@RequestParam("searchT") String searchT){
        Optional<List<ProductoDto>> productoDtos=productoService.findByNombreContainingIgnoreCase(searchT);
        return ResponseEntity.ok(productoDtos);
    }
    @GetMapping("/stock")
    public  ResponseEntity<Optional<List<ProductoDto>>> GetProductsInStock(){
        Optional<List<ProductoDto>> productoDtos=productoService.findByProductStockEqual(1);
        return ResponseEntity.ok(productoDtos);
    }
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductoToSaveDto producto){
        productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProductoToSaveDto productoDto){
        productoService.actualizarProducto(productoDto);
        return  ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productoService.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
