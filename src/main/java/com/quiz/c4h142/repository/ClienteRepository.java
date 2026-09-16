package com.quiz.c4h142.repository;

import com.quiz.c4h142.data.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}