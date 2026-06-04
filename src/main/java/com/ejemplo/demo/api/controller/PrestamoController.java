package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService; 
import com.ejemplo.demo.domain.model.Prestamo;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.RestController;
import com.ejemplo.demo.api.contract.SimulacionesApi;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;


@RestController

public class PrestamoController implements SimulacionesApi {

private final PrestamoService prestamoService;
    
    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }
    
    @Override
    public ResponseEntity<PrestamoResponse> simularPrestamo(PrestamoRequest prestamoRequest) {
        return ResponseEntity.ok(prestamoService.calcular(prestamoRequest));
    }

    @Override
    public ResponseEntity<List<Object>> listarTodos() {
        return ResponseEntity.ok((List<Object>) (List<?>) prestamoService.obtenerTodos());
    }

    @Override
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        prestamoService.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }
}