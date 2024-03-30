package com.vp.VentaProducto.Exception;

public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException() {
        super();
    }

    public PedidoNotFoundException(String message) {
        super(message);
    }

    public PedidoNotFoundException(Throwable cause) {
        super(cause);
    }
}
