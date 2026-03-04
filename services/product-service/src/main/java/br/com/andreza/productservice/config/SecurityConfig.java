package br.com.andreza.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.andreza.productservice.security.JwtProductFilter;

@Configuration
public class SecurityConfig {

    private final JwtProductFilter jwtProductFilter;

    public SecurityConfig(JwtProductFilter jwtProductFilter) {
        this.jwtProductFilter = jwtProductFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .addFilterBefore(
                jwtProductFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}

