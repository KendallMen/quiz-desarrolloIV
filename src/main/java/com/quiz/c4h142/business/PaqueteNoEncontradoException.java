package com.quiz.c4h142.business;

public class PaqueteNoEncontradoException extends RuntimeException {

    public PaqueteNoEncontradoException(Long paqueteId) {
        super("No existe un paquete con id " + paqueteId);
    }
}