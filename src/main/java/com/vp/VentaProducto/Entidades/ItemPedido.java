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
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantida;

    private Integer precioUnitario;

    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "productoId")
    private  Producto producto;
}
