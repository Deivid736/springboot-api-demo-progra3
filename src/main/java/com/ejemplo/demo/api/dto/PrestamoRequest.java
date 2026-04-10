package com.ejemplo.demo.api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PrestamoRequest(
    @NotNull(message = "El monto es obligatorio") 
    @Positive(message = "El monto debe ser mayor a 0") 
    BigDecimal monto,

    @NotNull(message = "La tasa es obligatoria") 
    @Positive(message = "La tasa debe ser mayor a 0") 
    BigDecimal tasaAnual,

    @Min(value = 1, message = "Plazo mínimo 1 mes") 
    @Max(value = 360, message = "Plazo máximo 360 meses") 
    int meses
) {}