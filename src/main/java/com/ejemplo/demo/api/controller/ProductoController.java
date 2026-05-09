package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.domain.model.Producto;
import com.ejemplo.demo.domain.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.ejemplo.demo.api.dto.ProductoRequest;
import com.ejemplo.demo.api.dto.ProductoResponse;
import jakarta.validation.Valid; 
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Manejo de productos en PostgreSQL")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public List<ProductoResponse> listar() {
        return productoService.obtenerTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un producto asociado a una categoría")
    public ProductoResponse crear(@Valid @RequestBody ProductoRequest request) {
    	return productoService.guardar(request);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por ID")
    public ResponseEntity<ProductoResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
        
    }
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto existente")
    public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un producto")
    public void eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
    }
}