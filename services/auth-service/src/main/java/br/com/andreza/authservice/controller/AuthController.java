package br.com.andreza.authservice.controller;

import br.com.andreza.authservice.dto.*;
import br.com.andreza.authservice.model.UserAuth;
import br.com.andreza.authservice.service.AuthService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public UserAuth register(@RequestBody UserAuth user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO dto) {

        String token = service.login(
            dto.getEmail(),
            dto.getPassword()
        );

        return ResponseEntity.ok(
            new LoginResponseDTO(token)
        );
  }
}
