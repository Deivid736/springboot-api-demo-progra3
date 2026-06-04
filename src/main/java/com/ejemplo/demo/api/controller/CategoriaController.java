package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.contract.CategoriasApi;
import com.ejemplo.demo.api.dto.Categoria; // <- EL DTO GENERADO POR EL YAML
import com.ejemplo.demo.domain.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoriaController implements CategoriasApi {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public ResponseEntity<List<Categoria>> listarCategorias() {

        List<com.ejemplo.demo.domain.model.Categoria> listaDominio = categoriaService.obtenerTodas();

        List<Categoria> listaDto = listaDominio.stream().map(catDominio -> {
            Categoria dto = new Categoria();
            dto.setId(catDominio.getId());
            dto.setNombre(catDominio.getNombre());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(listaDto);
    }

    @Override
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable("id") Long id) {
        com.ejemplo.demo.domain.model.Categoria catDominio = categoriaService.obtenerPorId(id);
        
        Categoria dto = new Categoria();
        if (catDominio != null) {
            dto.setId(catDominio.getId());
            dto.setNombre(catDominio.getNombre());
        }
        
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoriaDto) {
        com.ejemplo.demo.domain.model.Categoria entidad = new com.ejemplo.demo.domain.model.Categoria();
        entidad.setNombre(categoriaDto.getNombre());
        

        com.ejemplo.demo.domain.model.Categoria entidadGuardada = categoriaService.guardar(entidad);
        
        Categoria respuestaDto = new Categoria();
        respuestaDto.setId(entidadGuardada.getId());
        respuestaDto.setNombre(entidadGuardada.getNombre());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(respuestaDto);
    }

    @Override
    public ResponseEntity<Void> eliminarCategoria(@PathVariable("id") Long id) {
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}