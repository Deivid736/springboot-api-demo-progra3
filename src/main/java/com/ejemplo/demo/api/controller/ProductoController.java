package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.contract.ProductosApi;
import com.ejemplo.demo.api.dto.ProductoRequest;  // <- DTO GENERADO POR YAML
import com.ejemplo.demo.api.dto.ProductoResponse; // <- DTO GENERADO POR YAML
import com.ejemplo.demo.domain.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class ProductoController implements ProductosApi {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Override
    public ResponseEntity<List<ProductoResponse>> listarProductos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @Override
    public ResponseEntity<ProductoResponse> crearProducto(@Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(request));
    }

    @Override
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @Override
    public ResponseEntity<ProductoResponse> actualizarProducto(@PathVariable("id") Long id, @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoService.actualizar(id, request));
    }

    @Override
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}