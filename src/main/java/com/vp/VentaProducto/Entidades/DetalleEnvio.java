package com.vp.VentaProducto.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccion;

    private String transportadora;

    private Integer numeroGuia;

    @OneToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;
}
