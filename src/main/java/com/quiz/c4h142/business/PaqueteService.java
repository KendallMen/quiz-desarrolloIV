package com.quiz.c4h142.business;

import com.quiz.c4h142.data.Cliente;
import com.quiz.c4h142.data.Paquete;
import com.quiz.c4h142.dto.PaqueteRequestDto;
import com.quiz.c4h142.dto.PaqueteResponseDto;

public class PaqueteService {

	public Paquete toEntity(PaqueteRequestDto requestDto) {
		Paquete paquete = new Paquete();
		paquete.setCodigoRastreo(requestDto.getCodigoRastreo());
		paquete.setDescripcion(requestDto.getDescripcion());
		paquete.setPesoKg(requestDto.getPesoKg());
		paquete.setEstado(requestDto.getEstado());
		if (requestDto.getClienteId() != null) {
			Cliente cliente = new Cliente();
			cliente.setId(requestDto.getClienteId());
			paquete.setCliente(cliente);
		}
		return paquete;
	}

	public PaqueteResponseDto toResponseDto(Paquete paquete) {
		return new PaqueteResponseDto(
				paquete.getId(),
				paquete.getCodigoRastreo(),
				paquete.getDescripcion(),
				paquete.getPesoKg(),
				paquete.getEstado(),
				paquete.getCliente() != null ? paquete.getCliente().getId() : null
		);
	}
}
