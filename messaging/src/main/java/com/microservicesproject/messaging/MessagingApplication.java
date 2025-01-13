package com.microservicesproject.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

}
