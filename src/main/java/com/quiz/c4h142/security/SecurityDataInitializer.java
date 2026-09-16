package com.quiz.c4h142.security;

import com.quiz.c4h142.data.RolUsuario;
import com.quiz.c4h142.data.Usuario;
import com.quiz.c4h142.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityDataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityDataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRol(RolUsuario.ROLE_ADMIN);
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByUsername("operador").isEmpty()) {
            Usuario operador = new Usuario();
            operador.setUsername("operador");
            operador.setPassword(passwordEncoder.encode("operador123"));
            operador.setRol(RolUsuario.ROLE_OPERADOR);
            usuarioRepository.save(operador);
        }
    }
}