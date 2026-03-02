package br.com.andreza.userservice.repository;


import br.com.andreza.userservice.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   
    Optional<User> findByEmail(String email);
    
}
