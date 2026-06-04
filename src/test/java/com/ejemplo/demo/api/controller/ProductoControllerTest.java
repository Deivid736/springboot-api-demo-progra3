package com.ejemplo.demo.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ejemplo.demo.api.dto.ProductoRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearProducto_DebeRetornar201_CuandoEsValido() throws Exception {
        ProductoRequest request = new ProductoRequest();
        request.setNombre("Producto Test");
        request.setPrecio(99.99);
        request.setCategoriaId(1L); 

        mockMvc.perform(post("/api/v1/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void crearProducto_DebeRetornar400_CuandoNombreEsVacio() throws Exception {
        ProductoRequest request = new ProductoRequest();
        request.setNombre("");
        request.setPrecio(10.00);

        mockMvc.perform(post("/api/v1/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void obtenerProducto_DebeRetornar404_CuandoNoExiste() throws Exception {
        mockMvc.perform(get("/api/v1/productos/999")) 
                .andExpect(status().isNotFound());
    }
}