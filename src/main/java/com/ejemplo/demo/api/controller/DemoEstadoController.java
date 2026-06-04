package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.contract.DemoEstadoApi;
import com.ejemplo.demo.api.dto.EstadoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoEstadoController implements DemoEstadoApi {

    private Integer valorSingleton = 0;

    @Override
    public ResponseEntity<EstadoResponse> actualizarSingleton(@PathVariable("valor") Integer valor) {
        this.valorSingleton = valor;
        return ResponseEntity.ok(generarRespuesta("singleton", this.valorSingleton));
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerSingleton() {
        return ResponseEntity.ok(generarRespuesta("singleton", this.valorSingleton));
    }

    @Override
    public ResponseEntity<EstadoResponse> reiniciarSingleton() {
        this.valorSingleton = 0;
        return ResponseEntity.ok(generarRespuesta("singleton", this.valorSingleton));
    }

    @Override
    public ResponseEntity<EstadoResponse> actualizarManual(@PathVariable("valor") Integer valor) {
        EstadoManual manual = new EstadoManual();
        manual.setValor(valor);
        return ResponseEntity.ok(generarRespuesta("manual", manual.getValor()));
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerManual() {
        EstadoManual manual = new EstadoManual();
        return ResponseEntity.ok(generarRespuesta("manual", manual.getValor()));
    }

    private EstadoResponse generarRespuesta(String tipo, Integer valor) {
        EstadoResponse response = new EstadoResponse();
        response.setTipo(tipo);
        response.setValorActual(valor);
        return response;
    }

    private static class EstadoManual {
        private Integer valor = 0;
        public Integer getValor() { return valor; }
        public void setValor(Integer valor) { this.valor = valor; }
    }
}