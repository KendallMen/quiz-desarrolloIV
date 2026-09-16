package com.quiz.c4h142.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paquetes")
public class Paquete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String codigoRastreo;

	@Column(nullable = false)
	private String descripcion;

	@Column(nullable = false)
	private Double pesoKg;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoPaquete estado;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	public Paquete() {
	}

	public Paquete(Long id, String codigoRastreo, String descripcion, Double pesoKg, EstadoPaquete estado, Cliente cliente) {
		this.id = id;
		this.codigoRastreo = codigoRastreo;
		this.descripcion = descripcion;
		this.pesoKg = pesoKg;
		this.estado = estado;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoRastreo() {
		return codigoRastreo;
	}

	public void setCodigoRastreo(String codigoRastreo) {
		this.codigoRastreo = codigoRastreo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg(Double pesoKg) {
		this.pesoKg = pesoKg;
	}

	public EstadoPaquete getEstado() {
		return estado;
	}

	public void setEstado(EstadoPaquete estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
