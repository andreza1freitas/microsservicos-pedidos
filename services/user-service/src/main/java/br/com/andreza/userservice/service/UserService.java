package br.com.andreza.userservice.service;

import br.com.andreza.userservice.model.User;
import br.com.andreza.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
