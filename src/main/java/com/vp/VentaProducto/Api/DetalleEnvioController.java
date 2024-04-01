package com.vp.VentaProducto.Api;

import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioDto;
import com.vp.VentaProducto.Dtos.DetalleEnvio.DetalleEnvioToSaveDTO;
import com.vp.VentaProducto.Entidades.EstatusPedido;
import com.vp.VentaProducto.Servicios.DetalleEnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/detalleEnvios")
public class DetalleEnvioController {
    private final DetalleEnvioService detalleEnvioService;

    public DetalleEnvioController(DetalleEnvioService detalleEnvioService) {
        this.detalleEnvioService = detalleEnvioService;
    }

    @PostMapping
    public ResponseEntity<DetalleEnvioDto> create(@RequestBody DetalleEnvioToSaveDTO detalleEnvio){
        detalleEnvioService.guardarDetalleEnvio(detalleEnvio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DetalleEnvioDto>> getDetalleEnvioById(@PathVariable Long id){
        Optional<DetalleEnvioDto> detalleEnvio=Optional.ofNullable(detalleEnvioService.findById(id));
        return ResponseEntity.ok(detalleEnvio);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody DetalleEnvioToSaveDTO detalleEnvio){
        detalleEnvioService.actualizarDetalleEnvio(detalleEnvio);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        detalleEnvioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{pedidoId}" )
    public ResponseEntity<Optional<DetalleEnvioDto>> getDetalleEnvioPedidoByID(@PathVariable Long pedidoId){
        Optional<DetalleEnvioDto> detalleEnvio=detalleEnvioService.findByPedidoId(pedidoId);
        return ResponseEntity.ok(detalleEnvio);
    }

    @GetMapping("transportadora")
    public ResponseEntity<Optional<List<DetalleEnvioDto>>> getDetalleEnvioTransportadora(@RequestParam("transportadora") String transportadora){
        Optional<List<DetalleEnvioDto>> detalleEnvio=detalleEnvioService.findByTransportadora(transportadora);
        return ResponseEntity.ok(detalleEnvio);
    }

    @GetMapping("estatus")
    public ResponseEntity<Optional<List<DetalleEnvioDto>>> getDetalleEnvioEstatus(@RequestParam("estatus") EstatusPedido estado){
        Optional<List<DetalleEnvioDto>> detalleEnvio=detalleEnvioService.findByEstado(estado);
        return ResponseEntity.ok(detalleEnvio);
    }

    @GetMapping()
    public ResponseEntity<Optional<List<DetalleEnvioDto>>> getDetallesEnvio(){
        Optional<List<DetalleEnvioDto>> detalleEnvio=detalleEnvioService.getDestalleEnvio();
        return ResponseEntity.ok().body(detalleEnvio);
    }
}
