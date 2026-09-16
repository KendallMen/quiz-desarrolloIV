package com.quiz.c4h142.business;

import com.quiz.c4h142.data.Cliente;
import com.quiz.c4h142.data.EstadoPaquete;
import com.quiz.c4h142.data.Paquete;
import com.quiz.c4h142.repository.ClienteRepository;
import com.quiz.c4h142.repository.PaqueteRepository;
import com.quiz.c4h142.dto.PaqueteRequestDto;
import com.quiz.c4h142.dto.PaqueteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaqueteService {

	private final PaqueteRepository paqueteRepository;
	private final ClienteRepository clienteRepository;

	public PaqueteService(PaqueteRepository paqueteRepository, ClienteRepository clienteRepository) {
		this.paqueteRepository = paqueteRepository;
		this.clienteRepository = clienteRepository;
	}

	@Transactional
	public PaqueteResponseDto registrarPaquete(PaqueteRequestDto requestDto) {
		Paquete paquete = toEntity(requestDto);
		Paquete paqueteGuardado = paqueteRepository.save(paquete);
		return toResponseDto(paqueteGuardado);
	}

	@Transactional
	public PaqueteResponseDto actualizarPaquete(Long id, PaqueteRequestDto requestDto) {
		Paquete paqueteExistente = paqueteRepository.findById(id)
				.orElseThrow(() -> new PaqueteNoEncontradoException(id));
		Paquete paqueteActualizado = toEntity(requestDto);
		paqueteActualizado.setId(paqueteExistente.getId());
		Paquete paqueteGuardado = paqueteRepository.save(paqueteActualizado);
		return toResponseDto(paqueteGuardado);
	}

	@Transactional(readOnly = true)
	public PaqueteResponseDto obtenerPorId(Long id) {
		Paquete paquete = paqueteRepository.findById(id)
				.orElseThrow(() -> new PaqueteNoEncontradoException(id));
		return toResponseDto(paquete);
	}

	@Transactional
	public void eliminarPaquete(Long id) {
		Paquete paquete = paqueteRepository.findById(id)
				.orElseThrow(() -> new PaqueteNoEncontradoException(id));
		paqueteRepository.delete(paquete);
	}

	@Transactional(readOnly = true)
	public Page<PaqueteResponseDto> buscarPorEstado(EstadoPaquete estado, Pageable pageable) {
		return paqueteRepository.findByEstado(estado, pageable).map(this::toResponseDto);
	}

	private Paquete toEntity(PaqueteRequestDto requestDto) {
		if (requestDto.getPesoKg() != null && requestDto.getPesoKg() > 30.0) {
			throw new PesoExcedidoException(requestDto.getPesoKg());
		}

		Paquete paquete = new Paquete();
		paquete.setCodigoRastreo(requestDto.getCodigoRastreo());
		paquete.setDescripcion(requestDto.getDescripcion());
		paquete.setPesoKg(requestDto.getPesoKg());
		paquete.setEstado(requestDto.getEstado());
		if (requestDto.getClienteId() == null) {
			throw new ClienteNoEncontradoException(null);
		}

		Cliente cliente = clienteRepository.findById(requestDto.getClienteId())
				.orElseThrow(() -> new ClienteNoEncontradoException(requestDto.getClienteId()));
		paquete.setCliente(cliente);
		return paquete;
	}

	private PaqueteResponseDto toResponseDto(Paquete paquete) {
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
