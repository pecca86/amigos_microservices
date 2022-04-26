package com.pekka.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate
) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        // Build new customer with the builder pattern
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // Save customer to db, saveAndFlush saves it straight to the db
        customerRepository.saveAndFlush(customer);
        // check if customer is possible fraudster using our fraud microservice
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                // Eureka server takes care of porting the request to the right address
                "http://FRAUD/api/v1/fraud-check/{customerId}", // Before Eureka service server: "http://localhost:8081/api/v1/fraud-check/{customerId}"
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

    }
}
