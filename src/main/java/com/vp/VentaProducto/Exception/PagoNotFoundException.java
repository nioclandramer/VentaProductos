package com.vp.VentaProducto.Exception;

public class PagoNotFoundException extends RuntimeException{
    public PagoNotFoundException() {
        super();
    }

    public PagoNotFoundException(String message) {
        super(message);
    }

    public PagoNotFoundException(Throwable cause) {
        super(cause);
    }
}
