package com.ejemplo.demo.domain.service;
import java.time.Instant;
import com.ejemplo.demo.api.dto.SaludoResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaludoService {

    public SaludoResponse crearSaludo(String nombre) {
        String nombreNormalizado = normalizarNombre(nombre);
        String mensaje = "Hola, %s. Bienvenido a Spring Boot 3!".formatted(nombreNormalizado);
        SaludoResponse response = new SaludoResponse();
        response.setMensaje(mensaje);
        return response;
    }

    /*
    PASO 4 (EJERCICIO):
    - Modifica esta logica para personalizar el formato del nombre.
    - Ideas:
      1) Primera letra mayuscula y resto minuscula.
      2) Rechazar nombres con numeros.
      3) Agregar prefijo "Estudiante".
    */

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