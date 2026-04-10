package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/simulaciones")
@Tag(name = "Simulador de Préstamos", description = "Paso 8")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/prestamo")
    @Operation(summary = "Calcula la cuota de un préstamo")
    public PrestamoResponse simular(@Valid @RequestBody PrestamoRequest request) {
        return prestamoService.calcular(request);
    }
}