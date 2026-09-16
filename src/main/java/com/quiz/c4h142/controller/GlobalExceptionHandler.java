package com.quiz.c4h142.controller;

import com.quiz.c4h142.business.ClienteNoEncontradoException;
import com.quiz.c4h142.business.PaqueteNoEncontradoException;
import com.quiz.c4h142.business.PesoExcedidoException;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PesoExcedidoException.class)
    public ProblemDetail handlePesoExcedido(PesoExcedidoException exception, HttpServletRequest request) {
        return buildProblemDetail(
                HttpStatus.BAD_REQUEST,
                URI.create("https://api.c4h142.quiz/errors/peso-excedido"),
                "Peso excedido",
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(ClienteNoEncontradoException.class)
    public ProblemDetail handleClienteNoEncontrado(ClienteNoEncontradoException exception, HttpServletRequest request) {
        return buildProblemDetail(
                HttpStatus.NOT_FOUND,
                URI.create("https://api.c4h142.quiz/errors/cliente-no-encontrado"),
                "Cliente no encontrado",
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(PaqueteNoEncontradoException.class)
    public ProblemDetail handlePaqueteNoEncontrado(PaqueteNoEncontradoException exception, HttpServletRequest request) {
        return buildProblemDetail(
                HttpStatus.NOT_FOUND,
                URI.create("https://api.c4h142.quiz/errors/paquete-no-encontrado"),
                "Paquete no encontrado",
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentials(BadCredentialsException exception, HttpServletRequest request) {
        return buildProblemDetail(
                HttpStatus.UNAUTHORIZED,
                URI.create("https://api.c4h142.quiz/errors/credenciales-invalidas"),
                "Credenciales inválidas",
                "Usuario o contraseña incorrectos",
                request
        );
    }

    private ProblemDetail buildProblemDetail(HttpStatus status, URI type, String title, String detail, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setType(type);
        problemDetail.setTitle(title);
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        problemDetail.setProperty("timestamp", Instant.now().toString());
        return problemDetail;
    }
}