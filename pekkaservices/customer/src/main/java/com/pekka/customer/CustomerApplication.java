package com.pekka.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Annotate so the class a recognized as a Spring boot main class
@SpringBootApplication
@EnableEurekaClient // Registers this bean as a Eureka client
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
