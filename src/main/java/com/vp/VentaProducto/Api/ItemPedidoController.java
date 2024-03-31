package com.vp.VentaProducto.Api;


import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoDto;
import com.vp.VentaProducto.Dtos.ItemPedido.ItemPedidoToSaveDto;
import com.vp.VentaProducto.Servicios.ItemPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/itemsPedidos")
public class ItemPedidoController {
    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @PostMapping
    public ResponseEntity<ItemPedidoDto> create(@RequestBody ItemPedidoToSaveDto itemPedido){
        itemPedidoService.guardarItemPedido(itemPedido);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ItemPedidoDto>> getItempPedidoById(@PathVariable Long id){
        Optional<ItemPedidoDto> itemPedido=Optional.ofNullable(itemPedidoService.findById(id));
        return ResponseEntity.ok(itemPedido);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ItemPedidoToSaveDto itemPedido){
        itemPedidoService.actualizarItemPedido(itemPedido);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        itemPedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{pedidoId}" )
    public ResponseEntity<Optional<List<ItemPedidoDto>>> getItemPedidoPedidoByID(@PathVariable Long pedidoId){
        Optional<List<ItemPedidoDto>> itemPedido=itemPedidoService.findByPedidoId(pedidoId);
        return ResponseEntity.ok(itemPedido);
    }

    @GetMapping("/{productoId}" )
    public ResponseEntity<Optional<List<ItemPedidoDto>>> getItemPedidoProductoByID(@PathVariable Long productoId){
        Optional<List<ItemPedidoDto>> itemPedido=itemPedidoService.findByProductoId(productoId);
        return ResponseEntity.ok(itemPedido);
    }

    @GetMapping("/{productoId}" )
    public ResponseEntity<Optional<Integer>> getItemPedidoByTotalVentasPorProducto(@PathVariable Long productoId){
        Optional<Integer> itemPedido=itemPedidoService.totalVentasPorProducto(productoId);
        return ResponseEntity.ok(itemPedido);
    }

    @GetMapping()
    public ResponseEntity<Optional<List<ItemPedidoDto>>> getItemsPedido(){
        Optional<List<ItemPedidoDto>> pedido=itemPedidoService.getItemPedido();
        return ResponseEntity.ok().body(pedido);
    }
}
