package com.quiz.c4h142.controller;

import com.quiz.c4h142.business.PaqueteService;
import com.quiz.c4h142.data.EstadoPaquete;
import com.quiz.c4h142.dto.PaqueteRequestDto;
import com.quiz.c4h142.dto.PaqueteResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paquetes")
public class PaqueteController {

	private final PaqueteService paqueteService;

	public PaqueteController(PaqueteService paqueteService) {
		this.paqueteService = paqueteService;
	}

	@Operation(summary = "Crear un paquete", description = "Registra un paquete nuevo asociado a un cliente existente.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Paquete creado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos o reglas de negocio incumplidas"),
			@ApiResponse(responseCode = "404", description = "Cliente no encontrado")
	})
	@PostMapping
	public ResponseEntity<PaqueteResponseDto> crearPaquete(@Valid @RequestBody PaqueteRequestDto requestDto) {
		PaqueteResponseDto responseDto = paqueteService.registrarPaquete(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@Operation(summary = "Obtener un paquete por id", description = "Devuelve un paquete existente por su identificador.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Paquete encontrado"),
			@ApiResponse(responseCode = "404", description = "Paquete no encontrado")
	})
	@GetMapping("/{id}")
	public ResponseEntity<PaqueteResponseDto> obtenerPaquete(@PathVariable Long id) {
		return ResponseEntity.ok(paqueteService.obtenerPorId(id));
	}

	@Operation(summary = "Actualizar un paquete", description = "Actualiza un paquete existente respetando las reglas de negocio.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Paquete actualizado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos o reglas de negocio incumplidas"),
			@ApiResponse(responseCode = "404", description = "Paquete o cliente no encontrado")
	})
	@PutMapping("/{id}")
	public ResponseEntity<PaqueteResponseDto> actualizarPaquete(@PathVariable Long id, @Valid @RequestBody PaqueteRequestDto requestDto) {
		return ResponseEntity.ok(paqueteService.actualizarPaquete(id, requestDto));
	}

	@Operation(summary = "Eliminar un paquete", description = "Elimina un paquete existente por su identificador.")
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "Paquete eliminado"),
			@ApiResponse(responseCode = "404", description = "Paquete no encontrado")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPaquete(@PathVariable Long id) {
		paqueteService.eliminarPaquete(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Buscar paquetes por estado", description = "Devuelve una página de paquetes filtrada por estado.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Página de paquetes devuelta"),
			@ApiResponse(responseCode = "400", description = "Estado inválido")
	})
	@GetMapping("/estado/{estado}")
	public ResponseEntity<Page<PaqueteResponseDto>> buscarPorEstado(@PathVariable EstadoPaquete estado, Pageable pageable) {
		return ResponseEntity.ok(paqueteService.buscarPorEstado(estado, pageable));
	}
}