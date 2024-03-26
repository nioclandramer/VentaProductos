package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente,Long> {
    Optional<Cliente> findByEmail(String email);
    Optional<List<Cliente>> findByDireccion(String direccion);
    Optional<List<Cliente>> findByNombreStartingWith(String nombre);
}
