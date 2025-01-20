//package com.microservicesproject.apigateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
////import org.springframework.security.config.web.server.ServerHttpSecurity;
////import org.springframework.security.oauth2.jwt.JwtDecoders;
////import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
////import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
////import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
////import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
////import org.springframework.security.web.server.SecurityWebFilterChain;
////
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchange -> exchange
//                        .pathMatchers("/api/profiles/**").permitAll()
//                        .pathMatchers("/api/messaging/**").permitAll()
//                        .anyExchange().permitAll())
//                .build();
//    }
//}
