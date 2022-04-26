package com.pekka.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    @Bean
    // Implements Round-Robin as default
    @LoadBalanced // Tells spring, that there are multiple options where the request can go, otherwise we'll get a 500
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
