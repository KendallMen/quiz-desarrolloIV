package com.quiz.c4h142.dto;

import com.quiz.c4h142.data.EstadoPaquete;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaqueteRequestDto {

	@NotBlank
	private String codigoRastreo;

	@NotBlank
	private String descripcion;

	@NotNull
	@Positive
	private Double pesoKg;

	@NotNull
	private EstadoPaquete estado;

	@NotNull
	private Long clienteId;

	public PaqueteRequestDto() {
	}

	public PaqueteRequestDto(String codigoRastreo, String descripcion, Double pesoKg, EstadoPaquete estado, Long clienteId) {
		this.codigoRastreo = codigoRastreo;
		this.descripcion = descripcion;
		this.pesoKg = pesoKg;
		this.estado = estado;
		this.clienteId = clienteId;
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

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
}
