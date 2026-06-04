package com.ejemplo.demo.api.controller;

import org.springframework.http.ResponseEntity;
import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.api.dto.SaludoRequest;
import com.ejemplo.demo.domain.service.SaludoService;
import com.ejemplo.demo.api.contract.WorkshopApi;
import com.ejemplo.demo.api.dto.GetWorkshopHealth200Response;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController implements WorkshopApi{

    private final SaludoService saludoService;

    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    @Override
    public ResponseEntity<GetWorkshopHealth200Response> getWorkshopHealth() {
        GetWorkshopHealth200Response response = new GetWorkshopHealth200Response();
        response.setEstado("ok");
        response.setMensaje("Workshop Spring Boot activo");
        return ResponseEntity.ok(response);
    }
    
    @Override
    public ResponseEntity<SaludoResponse> saludarPorGet(String nombre) {
        return ResponseEntity.ok(saludoService.crearSaludo(nombre));
    }

    @Override
    public ResponseEntity<SaludoResponse> saludarPorPost(SaludoRequest saludoRequest) {
    	return ResponseEntity.ok(saludoService.crearSaludo(saludoRequest.getNombre()));
    }
}