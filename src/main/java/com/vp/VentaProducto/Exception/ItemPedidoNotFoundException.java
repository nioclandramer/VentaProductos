package com.vp.VentaProducto.Exception;

public class ItemPedidoNotFoundException extends RuntimeException{
    public ItemPedidoNotFoundException() {
        super();
    }

    public ItemPedidoNotFoundException(String message) {
        super(message);
    }

    public ItemPedidoNotFoundException(Throwable cause) {
        super(cause);
    }
}
