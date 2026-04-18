package com.ejemplo.demo.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity

public class Prestamo {
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Integer getMeses() {
		return meses;
	}

	public void setMeses(Integer meses) {
		this.meses = meses;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal monto;
    private Integer meses;

    // Relación: Muchos préstamos pertenecen a un solo cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Esta es la llave foránea en la tabla
    private Cliente cliente;
}