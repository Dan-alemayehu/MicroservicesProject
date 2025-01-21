//package com.microservicesproject.apigateway.config;
//
//import com.microservicesproject.apigateway.security.JwtAuthenticationWebFilter;
//import com.microservicesproject.apigateway.util.JwtUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    private final JwtUtils jwtUtils;
//
//    public SecurityConfig(final JwtUtils jwtUtils) {
//        this.jwtUtils = jwtUtils;
//    }
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/api/auth/**").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .addFilterAt(new JwtAuthenticationWebFilter(jwtUtils), SecurityWebFiltersOrder.AUTHENTICATION)
//                .build();
//    }
//}