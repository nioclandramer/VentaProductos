package com.vp.VentaProducto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestVentaProductoApplication {

	public static void main(String[] args) {
		SpringApplication.from(VentaProductoApplication::main).with(TestVentaProductoApplication.class).run(args);
	}

}
