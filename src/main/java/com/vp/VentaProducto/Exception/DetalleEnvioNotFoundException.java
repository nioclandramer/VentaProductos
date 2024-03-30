package com.vp.VentaProducto.Exception;

public class DetalleEnvioNotFoundException extends RuntimeException{
    public DetalleEnvioNotFoundException() {
        super();
    }

    public DetalleEnvioNotFoundException(String message) {
        super(message);
    }

    public DetalleEnvioNotFoundException(Throwable cause) {
        super(cause);
    }
}
