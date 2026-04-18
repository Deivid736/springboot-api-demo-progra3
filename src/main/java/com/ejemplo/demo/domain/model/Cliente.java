package com.ejemplo.demo.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity

public class Cliente {
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDpi() {
		return dpi;
	}

	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String dpi;

    // Relación: Un cliente puede tener una lista de muchos préstamos
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos;
}