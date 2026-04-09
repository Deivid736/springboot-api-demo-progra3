package com.ejemplo.demo.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.ejemplo.demo.domain.service.SaludoService;
import org.mockito.Mockito;

@WebMvcTest(SaludoController.class)
class SaludoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private SaludoService saludoService;

    @Test
    @DisplayName("Debe responder health del workshop")
    void debeResponderHealthDelWorkshop() throws Exception {
    	
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"));
    }


    @Test
    @DisplayName("1) GET /api/v1/saludos?nombre=Ana -> 200")
    void testGetSaludoAna() throws Exception {
        com.ejemplo.demo.api.dto.SaludoResponse respuestaFalsa = 
            new com.ejemplo.demo.api.dto.SaludoResponse(
                "Hola, Estudiante Ana. Bienvenido a Spring Boot 3!", 
                java.time.Instant.now()
            );

        Mockito.when(saludoService.crearSaludo("Ana"))
               .thenReturn(respuestaFalsa);

        mockMvc.perform(get("/api/v1/saludos").param("nombre", "Ana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Hola, Estudiante Ana. Bienvenido a Spring Boot 3!"));
    }
    
    @Test
    @DisplayName("2) POST /api/v1/saludos con nombre vacio -> 400")
    void testPostNombreVacio() throws Exception {
        String jsonRequest = "{\"nombre\":\"\"}";

        mockMvc.perform(post("/api/v1/saludos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR"));
    } 
    }