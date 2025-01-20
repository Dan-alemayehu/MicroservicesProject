package com.microservicesproject.apigateway.service;

import com.microservicesproject.apigateway.util.JwtUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final String mockUsername = "admin";
    private final String mockPassword = "password";

    private final JwtUtils jwtUtils;

    public AuthenticationService(JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    public String login(String username, String password){
        if(mockUsername.equals(username) && mockPassword.equals(password)){
            return jwtUtils.generateToken(username);
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
