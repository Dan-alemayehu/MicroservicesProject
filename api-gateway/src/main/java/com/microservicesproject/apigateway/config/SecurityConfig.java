package com.microservicesproject.apigateway.config;

import com.microservicesproject.apigateway.util.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.JwtDecoders;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
@Configuration
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    public SecurityConfig(final JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/auth/**").permitAll()
                        .anyExchange().authenticated()
                )
                .build();
    }
}
