package ar.edu.utn.frc.bso.servicioAlumnos.services;

import ar.edu.utn.frc.bso.servicioAlumnos.entities.AuthResult;
import ar.edu.utn.frc.bso.servicioAlumnos.entities.User;
import ar.edu.utn.frc.bso.servicioAlumnos.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResult validateAuth(String auth, String expectedRole) {
        // Basic anVhbjoxMjM0NQ==
        if (auth == null || !auth.startsWith("Basic ")) {
            return AuthResult.INVALID_USER;
        }
        String encoded = auth.substring(6);
        log.info("Encoded:{}", encoded);
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        String decoded = new String(decodedBytes, StandardCharsets.UTF_8);
        log.info("Decoded:{}", decoded);
        String[] parts = decoded.split(":");
        if (parts.length != 2) {
            return AuthResult.INVALID_USER;
        }
        List<User> all = userRepository.findAll();
        Optional<User> user = all.stream().filter(x -> x.getUsername().equals(parts[0]) && x.getPassword().equals(parts[1]))
                .findFirst();
        if (user.isEmpty()) {
            return AuthResult.INVALID_USER;
        }
        User usuarioAutenticado = user.get();
        if (!expectedRole.equals(usuarioAutenticado.getRole())) {
            return AuthResult.FORBIDDEN;
        }

        return AuthResult.OK;
    }
}
