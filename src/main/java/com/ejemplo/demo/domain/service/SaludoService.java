package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.SaludoResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaludoService {
    public SaludoResponse crearSaludo(String nombre) {
        String nombreNormalizado = normalizarNombre(nombre);
        String mensaje = "Hola, %s. Bienvenido a Spring Boot 3!".formatted(nombreNormalizado);
        return new SaludoResponse(mensaje, Instant.now());
    }

    String normalizarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Estudiante Mundo";
        }

        nombre = nombre.trim();

        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            throw new IllegalArgumentException("El nombre no debe contener números.");
        }

        nombre = nombre.toLowerCase();

        String[] partes = nombre.split("\\s+");
        StringBuilder nombreFormateado = new StringBuilder();

        for (String parte : partes) {
            if (!parte.isEmpty()) {
                String palabra = parte.substring(0, 1).toUpperCase() + parte.substring(1);
                nombreFormateado.append(palabra).append(" ");
            }
        }

        return "Estudiante " + nombreFormateado.toString().trim();
    }
}
