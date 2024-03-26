package com.vp.VentaProducto.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float totalPago;

    private LocalDateTime fechaPago;

    private MetodoDePago metodoDePago;

    @OneToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;
}
