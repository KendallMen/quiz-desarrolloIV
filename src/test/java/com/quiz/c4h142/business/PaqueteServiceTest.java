package com.quiz.c4h142.business;

import com.quiz.c4h142.data.Cliente;
import com.quiz.c4h142.data.EstadoPaquete;
import com.quiz.c4h142.data.Paquete;
import com.quiz.c4h142.dto.PaqueteRequestDto;
import com.quiz.c4h142.dto.PaqueteResponseDto;
import com.quiz.c4h142.repository.ClienteRepository;
import com.quiz.c4h142.repository.PaqueteRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaqueteServiceTest {

    @Mock
    private PaqueteRepository paqueteRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PaqueteService paqueteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCedula("1-1111-1111");
        cliente.setNombre("Kendall Men");
        cliente.setCorreo("kendall.mendezcalderon@ucr.a.cr");
    }

    @Test
    void registrarPaquete_deberiaRetornarRespuestaCuandoElPesoEsValido() {
        PaqueteRequestDto requestDto = new PaqueteRequestDto(
                "RASTREO-001",
                "Paquete de prueba",
                25.0,
                EstadoPaquete.REGISTRADO,
                1L
        );

        Paquete paqueteGuardado = new Paquete();
        paqueteGuardado.setId(10L);
        paqueteGuardado.setCodigoRastreo(requestDto.getCodigoRastreo());
        paqueteGuardado.setDescripcion(requestDto.getDescripcion());
        paqueteGuardado.setPesoKg(requestDto.getPesoKg());
        paqueteGuardado.setEstado(requestDto.getEstado());
        paqueteGuardado.setCliente(cliente);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(paqueteRepository.save(org.mockito.ArgumentMatchers.any(Paquete.class))).thenReturn(paqueteGuardado);

        PaqueteResponseDto responseDto = paqueteService.registrarPaquete(requestDto);

        assertNotNull(responseDto);
        assertNotNull(responseDto.getId());
        verify(clienteRepository).findById(1L);
        verify(paqueteRepository).save(org.mockito.ArgumentMatchers.any(Paquete.class));
    }

    @Test
    void registrarPaquete_deberiaLanzarPesoExcedidoExceptionCuandoSupera30Kg() {
        PaqueteRequestDto requestDto = new PaqueteRequestDto(
                "RASTREO-002",
                "Paquete pesado",
                31.0,
                EstadoPaquete.REGISTRADO,
                1L
        );

        assertThrows(PesoExcedidoException.class, () -> paqueteService.registrarPaquete(requestDto));
    }
}
