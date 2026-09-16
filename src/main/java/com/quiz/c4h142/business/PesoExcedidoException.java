package com.quiz.c4h142.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PesoExcedidoException extends RuntimeException {

    public PesoExcedidoException(Double pesoKg) {
        super("El pesoKg del paquete no puede ser mayor a 30.0 kg. Valor recibido: " + pesoKg);
    }
}