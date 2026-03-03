package br.com.andreza.authservice.repository;

import br.com.andreza.authservice.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository
        extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findByEmail(String email);
}
