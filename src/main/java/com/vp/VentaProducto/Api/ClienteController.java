package com.vp.VentaProducto.Api;

import com.vp.VentaProducto.Dtos.Cliente.ClienteDto;
import com.vp.VentaProducto.Dtos.Cliente.ClienteToSaveDto;
import com.vp.VentaProducto.Servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/clientes")
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

    @GetMapping("/email")
    public ResponseEntity<Optional<ClienteDto>>searchClienteEmail(@RequestParam("email") String email){
        Optional<ClienteDto> cliente=clienteService.findByEmail(email);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/direccion")
    public ResponseEntity<Optional<List<ClienteDto>>>searchClientesDireccion(@RequestParam("direccion") String direccion){
        Optional<List<ClienteDto>> cliente=clienteService.findByDireccion(direccion);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/searchName")
    public ResponseEntity<Optional<List<ClienteDto>>>searchClientesNombreStartingWith(@RequestParam("searchName") String searchName){
        Optional<List<ClienteDto>> cliente=clienteService.findByNombreStartingWith(searchName);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping()
    public ResponseEntity<Optional<List<ClienteDto>>> getClientes(){
        Optional<List<ClienteDto>> clienteDtos=clienteService.getAllClientes();
        return ResponseEntity.ok().body(clienteDtos);
    }

}
