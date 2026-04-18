package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService; 
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.ejemplo.demo.domain.model.Prestamo;
import java.util.List;

@RestController
@RequestMapping("/api/v1/simulaciones")
@Tag(name = "Simulador de Préstamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping("/prestamo")
    @Operation(summary = "Calcula la simulación")
    public PrestamoResponse simular(@Valid @RequestBody PrestamoRequest request) {
        return prestamoService.calcular(request);
    }
    
    @GetMapping
    @Operation(summary = "Obtiene la lista de todos los préstamos guardados")
    public List<Prestamo> listarTodos() {
        return prestamoService.obtenerTodos();
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un préstamo por su ID")
    public void eliminar(@PathVariable Long id) {
        prestamoService.eliminarPrestamo(id);
    }
}