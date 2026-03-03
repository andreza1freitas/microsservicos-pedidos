package br.com.andreza.authservice.service;

import br.com.andreza.authservice.model.UserAuth;
import br.com.andreza.authservice.repository.UserAuthRepository;
import br.com.andreza.authservice.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserAuthRepository repository;
    private final JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserAuthRepository repository, JwtService jwtService, 
    		           PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String email, String password) {

        UserAuth user = repository.findByEmail(email)
            .orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
       	     throw new RuntimeException("Senha inválida");
        }

        return jwtService.generateToken(email);
    }

    public UserAuth register(UserAuth user) {

    	user.setPassword(
    		    passwordEncoder.encode(user.getPassword()));

        user.setRole("USER");

        return repository.save(user);
    }
}
