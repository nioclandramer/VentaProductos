package com.vp.VentaProducto.Exception;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException() {
        super();
    }

    public ProductoNotFoundException(String message) {
        super(message);
    }

    public ProductoNotFoundException(Throwable cause) {
        super(cause);
    }
}
