package com.vp.VentaProducto.Exception;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException() {
        super();
    }

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException(Throwable cause) {
        super(cause);
    }
}
