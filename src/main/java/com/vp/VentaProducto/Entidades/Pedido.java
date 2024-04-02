package com.vp.VentaProducto.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaPedido;

    private EstatusPedido estado;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos;

    @OneToOne(mappedBy = "pedido")
    private DetalleEnvio detalleEnvio;

    @OneToOne(fetch = FetchType.EAGER,mappedBy = "pedido")
    private Pago pago;
}
