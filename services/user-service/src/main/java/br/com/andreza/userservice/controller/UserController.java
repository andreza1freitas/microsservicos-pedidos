package br.com.andreza.userservice.controller;


import br.com.andreza.userservice.dto.UserRequestDTO;
import br.com.andreza.userservice.dto.UserResponseDTO;
import br.com.andreza.userservice.model.User;
import br.com.andreza.userservice.service.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserResponseDTO create(
        @RequestBody @Valid UserRequestDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        User saved = service.create(user);

        return toResponse(saved);
    }

    @GetMapping
    public List<UserResponseDTO> list() {

        return service.findAll()
            .stream()
            .map(this::toResponse)
            .toList();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id) {

        return toResponse(service.findById(id));
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(
        @PathVariable Long id,
        @RequestBody @Valid UserRequestDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return toResponse(service.update(id, user));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        service.delete(id);
    }

    private UserResponseDTO toResponse(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }
}