package com.vp.VentaProducto.Api;

import com.vp.VentaProducto.Dtos.Pago.PagoDto;
import com.vp.VentaProducto.Dtos.Pago.PagoToSaveDto;
import com.vp.VentaProducto.Entidades.MetodoDePago;
import com.vp.VentaProducto.Servicios.PagoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<PagoDto> create(@RequestBody PagoToSaveDto pago){
        pagoService.guardarPago(pago);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PagoDto>> getPagoById(@PathVariable Long id){
        Optional<PagoDto> pago=Optional.ofNullable(pagoService.findById(id));
        return ResponseEntity.ok(pago);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody PagoToSaveDto pago){
        pagoService.actualizarPago(pago);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pagoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dateInRange")
    public ResponseEntity<Optional<List<PagoDto>>> getPagoByFechaDeInicioYFechaEntrega(@RequestParam LocalDateTime fechaInicio,@RequestParam LocalDateTime fechaFinal){
        Optional<List<PagoDto>> pago=pagoService.findByFechaPagoBetween(fechaInicio,fechaFinal);
        return  ResponseEntity.ok(pago);
    }

    @GetMapping("/{pagoId}" )
    public ResponseEntity<Optional<PagoDto>> getPagoByIdAndMetodoDePago(@PathVariable Long pagoId, MetodoDePago metodoDePago){
        Optional<PagoDto> pago=pagoService.findByIdAndMetodoDePago(pagoId,metodoDePago);
        return ResponseEntity.ok(pago);
    }

    @GetMapping()
    public ResponseEntity<Optional<List<PagoDto>>> getPagos(){
        Optional<List<PagoDto>> pago=pagoService.getPago();
        return ResponseEntity.ok().body(pago);
    }
}
