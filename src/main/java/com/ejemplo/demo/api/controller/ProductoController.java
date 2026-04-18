package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.domain.model.Producto;
import com.ejemplo.demo.domain.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Productos", description = "Manejo de productos en PostgreSQL")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public List<Producto> listar() {
        return productoService.obtenerTodos();
    }

    @PostMapping("/categoria/{categoriaId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un producto asociado a una categoría")
    public Producto crear(@PathVariable Long categoriaId, @RequestBody Producto producto) {
        return productoService.guardar(producto, categoriaId);
    }
}