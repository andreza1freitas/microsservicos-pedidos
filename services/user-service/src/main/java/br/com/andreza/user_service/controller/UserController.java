package br.com.andreza.user_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // Endpoint de saúde para verificar se o serviço está funcionando
    @GetMapping("/users/health")
    public String health() {
        return "User Service OK";
    }
}
