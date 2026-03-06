package br.com.andreza.orderservice.client;

import br.com.andreza.orderservice.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://user-service:8081")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserResponseDTO getUserById(@PathVariable Long id);

}

