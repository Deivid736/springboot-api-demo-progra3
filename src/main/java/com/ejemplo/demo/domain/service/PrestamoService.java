package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.model.Prestamo;
import com.ejemplo.demo.domain.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    public PrestamoResponse calcular(PrestamoRequest req) {
    	Double monto = req.getMonto();
    	Double tasaAnual = req.getTasaAnual();
        int meses = req.getMeses();

        double P = monto;
        double r = tasaAnual / 12 / 100;
        int n = meses;

        double cuota = (P * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

        BigDecimal cuotaMensual = BigDecimal.valueOf(cuota).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPagar = cuotaMensual.multiply(BigDecimal.valueOf(n)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal interesTotal = totalPagar.subtract(BigDecimal.valueOf(monto)).setScale(2, RoundingMode.HALF_UP);

        Prestamo entidad = new Prestamo();
        entidad.setMonto(BigDecimal.valueOf(monto));
        entidad.setMeses(n);
        prestamoRepository.save(entidad);

        PrestamoResponse response = new PrestamoResponse();
        response.setCuotaMensual(cuotaMensual.doubleValue());
        response.setInteresTotal(interesTotal.doubleValue());
        response.setTotalPagar(totalPagar.doubleValue());

        return response;
    }

    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    public void eliminarPrestamo(Long id) {
        prestamoRepository.deleteById(id);
    }
}