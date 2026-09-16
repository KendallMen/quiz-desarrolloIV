package com.quiz.c4h142.business;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNoEncontradoException extends RuntimeException {

    public ClienteNoEncontradoException(Long clienteId) {
        super(clienteId == null
                ? "El cliente es obligatorio para registrar el paquete"
                : "No existe un cliente con id " + clienteId);
    }
}