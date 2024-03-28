package com.vp.VentaProducto.Repositorios;

import com.vp.VentaProducto.AstractIntegrationBDTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PedidoRepositoryTest extends AstractIntegrationBDTest {
    PedidoRepository pedidoRepository;

    public PedidoRepositoryTest(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @BeforeEach
    void setUp() {
        pedidoRepository.deleteAll();
    }
    @Test
        void Give_WhenCreate_ThenIsSaved(){
            //Give

             //When

            //then

    }



}