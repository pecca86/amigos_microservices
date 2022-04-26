package com.pekka.customer;

import com.pekka.clients.fraud.FraudCheckResponse;
import com.pekka.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate,
        FraudClient fraudClient
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
        // check if customer is possible fraudster using our fraud microservice through our Feign client service
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }

    }
}
