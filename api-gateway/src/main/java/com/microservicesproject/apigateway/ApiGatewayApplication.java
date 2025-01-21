package com.microservicesproject.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApiGatewayApplication.class, args);

//        JwtUtils jwtUtils = context.getBean(JwtUtils.class);
//        String testToken = jwtUtils.generateTestToken("johnathan_Doe");
//        System.out.println("Generated Test JWT Token: " + testToken);

    }
}