package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService; // Importante para que lo encuentre
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class PrestamoControllerTest {

    private final PrestamoService service = new PrestamoService();

    @Test
    void testSimulacionExitosa() {
        PrestamoRequest req = new PrestamoRequest(new BigDecimal("1000"), new BigDecimal("10"), 12);
        PrestamoResponse res = service.calcular(req);
        
        assertNotNull(res);
        System.out.println("✅ Test Exitoso: Cuota " + res.cuotaMensual());
    }
}