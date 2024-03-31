package com.vp.VentaProducto.Api;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoDto;
import com.vp.VentaProducto.Dtos.Pedido.PedidoToSaveDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoDto;
import com.vp.VentaProducto.Dtos.Producto.ProductoToSaveDto;
import com.vp.VentaProducto.Servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;
    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @GetMapping
    public ResponseEntity <Optional<List<PedidoDto>>> getAllpedidos(){
        Optional<List<PedidoDto>> pedidoDtos= pedidoService.getAllPedido();
        return ResponseEntity.ok().body(pedidoDtos);
    }
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody PedidoToSaveDto pedido){
        pedidoService.guardarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody PedidoToSaveDto pedido){
        pedidoService.actualizarPedido(pedido);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PedidoDto>> getPedidoByID(@PathVariable Long id){
        Optional<PedidoDto> pedidoDto= Optional.ofNullable(pedidoService.findById(id));
        return ResponseEntity.ok(pedidoDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/dateInRange")
    public ResponseEntity<Optional<List<PedidoDto>>> GetPedidosInDateRange(@RequestParam LocalDateTime finicio,@RequestParam LocalDateTime ffinal){
        Optional<List<PedidoDto>>pedidoDtos=pedidoService.findByFechaPedidoBetween(finicio,ffinal);
        return  ResponseEntity.ok(pedidoDtos);
    }
    @GetMapping("/Cliente")
    public ResponseEntity<Optional<List<PedidoDto>>> GetPedidosByCliente(@RequestBody ClienteDto cliente){
        Optional<List<PedidoDto>> pedidoDtos=pedidoService.findByClienteWhithItemPedidos(cliente);
        return ResponseEntity.ok(pedidoDtos);
    }
}
