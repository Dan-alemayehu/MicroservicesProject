//package com.microservicesproject.messaging.configuration;
//
//package com.microservicesproject.messaging.configuration;
//
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.context.annotation.Bean;
//
//@KeycloakConfiguration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/public/**").permitAll() // Public endpoints
//                        .anyRequest().authenticated() // All other requests need authentication
//                )
//                .oauth2ResourceServer()
//                .jwt(); // Use JWT for token validation
//
//        return http.build();
//    }
//}