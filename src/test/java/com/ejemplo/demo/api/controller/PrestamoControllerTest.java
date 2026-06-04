package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.repository.PrestamoRepository;
import com.ejemplo.demo.domain.service.PrestamoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PrestamoControllerTest {

    @Autowired
    private PrestamoService service;

    @MockBean
    private PrestamoRepository prestamoRepository;

    @Test
    void testSimulacionExitosa() {
        PrestamoRequest req = new PrestamoRequest();
        req.setMonto(1000.0);
        req.setTasaAnual(10.0);
        req.setMeses(12);

        Mockito.when(prestamoRepository.save(Mockito.any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PrestamoResponse res = service.calcular(req);

        assertNotNull(res);
        assertNotNull(res.getCuotaMensual());
        assertNotNull(res.getInteresTotal());
        assertNotNull(res.getTotalPagar());

        System.out.println("✅ Test préstamo exitoso: cuota " + res.getCuotaMensual());
    }
}