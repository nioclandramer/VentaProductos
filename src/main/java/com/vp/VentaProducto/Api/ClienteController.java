package com.vp.VentaProducto.Api;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteToSaveDto;
import com.vp.VentaProducto.Servicios.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Api/v1/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ClienteToSaveDto cliente){
        clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClienteDto>> getProductoByID(@PathVariable Long id){
        Optional<ClienteDto> cliente= Optional.ofNullable(clienteService.findById(id));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ClienteToSaveDto cliente){
        clienteService.actualizarCliente(cliente);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clienteService.deleteByID(id);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/searchT")
    public ResponseEntity<Optional<ClienteDto>>searchClienteEmail(@RequestParam("searchT") String emailT){
        Optional<ClienteDto> cliente=clienteService.findByEmail(emailT);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/searchT")
    public ResponseEntity<Optional<List<ClienteDto>>>searchClientesDireccion(@RequestParam("searchT") String direccionT){
        Optional<List<ClienteDto>> cliente=clienteService.findByDireccion(direccionT);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/searchT")
    public ResponseEntity<Optional<List<ClienteDto>>>searchClientesNombreStartingWith(@RequestParam("searchT") String nombreT){
        Optional<List<ClienteDto>> cliente=clienteService.findByNombreStartingWith(nombreT);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping()
    public ResponseEntity<Optional<List<ClienteDto>>> getClientes(){
        Optional<List<ClienteDto>> clienteDtos=clienteService.getAllClientes();
        return ResponseEntity.ok().body(clienteDtos);
    }

}
