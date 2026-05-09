package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.domain.model.Categoria;
import com.ejemplo.demo.domain.repository.CategoriaRepository;
import com.ejemplo.demo.domain.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ejemplo.demo.api.dto.ProductoRequest;
import com.ejemplo.demo.api.dto.ProductoResponse;
import com.ejemplo.demo.domain.model.Producto;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProductoResponse> obtenerTodos() {
        return productoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ProductoResponse obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Producto no encontrado con ID: " + id));
        return convertirADTO(producto);
    }

    @Transactional
    public ProductoResponse guardar(ProductoRequest request) {
        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setCategoria(categoria);

        return convertirADTO(productoRepository.save(producto));
    }
 
    @Transactional
    public ProductoResponse actualizar(Long id, ProductoRequest request) {

        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        productoExistente.setNombre(request.getNombre());
        productoExistente.setPrecio(request.getPrecio());
        productoExistente.setCategoria(categoria);

        return convertirADTO(productoRepository.save(productoExistente));
    }
    

    @Transactional
    public void eliminar(Long id) {
    	Producto producto = productoRepository.findById(id)
    	        .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        productoRepository.delete(producto);
    }
    
    private ProductoResponse convertirADTO(Producto producto) {
        ProductoResponse dto = new ProductoResponse();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        if (producto.getCategoria() != null) {
            dto.setCategoriaNombre(producto.getCategoria().getNombre());
        }
        return dto;
    }
}