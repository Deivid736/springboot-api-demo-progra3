package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PrestamoService {

    public PrestamoResponse calcular(PrestamoRequest req) {
        double P = req.monto().doubleValue();
        double r = req.tasaAnual().doubleValue() / 12 / 100; 
        int n = req.meses();

     
        double cuota = (P * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

        BigDecimal cuotaMensual = new BigDecimal(cuota).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPagar = cuotaMensual.multiply(new BigDecimal(n)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interesTotal = totalPagar.subtract(req.monto()).setScale(2, RoundingMode.HALF_UP);

        return new PrestamoResponse(cuotaMensual, interesTotal, totalPagar);
    }
}