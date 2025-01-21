//package com.microservicesproject.apigateway.controller;
//
//import com.microservicesproject.apigateway.model.LoginRequest;
//import com.microservicesproject.apigateway.model.LoginResponse;
//import com.microservicesproject.apigateway.service.AuthenticationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService authenticationService;
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
//        String token = authenticationService.login(request.getUsername(), request.getPassword());
//        return ResponseEntity.ok(new LoginResponse(token));
//    }
//}
