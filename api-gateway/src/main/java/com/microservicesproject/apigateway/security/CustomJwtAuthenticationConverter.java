package com.microservicesproject.apigateway.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, Mono<AbstractAuthenticationToken>> {

    @Override
    public Mono<AbstractAuthenticationToken> convert(Jwt jwt) {
        // Extract roles from the JWT
        List<String> roles = extractRolesFromJwt(jwt);

        // Convert roles to GrantedAuthority objects
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // Create and return a JwtAuthenticationToken with authorities
        AbstractAuthenticationToken authenticationToken = new JwtAuthenticationToken(jwt, authorities);
        return Mono.just(authenticationToken);
    }

    private List<String> extractRolesFromJwt(Jwt jwt) {
        // Check if "realm_access" contains roles
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null && realmAccess.containsKey("roles")) {
            return ((List<?>) realmAccess.get("roles"))
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }

        // Check if "resource_access" contains roles for a specific client
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess != null && resourceAccess.containsKey("messaging-service")) {
            Map<String, Object> messagingService = (Map<String, Object>) resourceAccess.get("messaging-service");
            if (messagingService.containsKey("roles")) {
                return ((List<?>) messagingService.get("roles"))
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.toList());
            }
        }

        // Fallback: Default role if none are found
        return List.of("ROLE_USER");
    }

}