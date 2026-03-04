package br.com.andreza.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.andreza.orderservice.security.JwtOrderFilter;

@Configuration
public class SecurityConfig {
	
    private final JwtOrderFilter jwtOrderFilter;

    public SecurityConfig(JwtOrderFilter jwtOrderFilter) {
        this.jwtOrderFilter = jwtOrderFilter;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .addFilterBefore(
                jwtOrderFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

}
