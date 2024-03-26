package com.vp.VentaProducto.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer precio;

    private Integer Stock;

    @OneToMany(mappedBy = "producto")
    private List<ItemPedido> itemPedidos;
}
