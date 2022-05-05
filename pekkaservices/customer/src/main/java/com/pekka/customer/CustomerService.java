package com.pekka.customer;

import com.pekka.amqp.RabbitMQMessageProducer;
import com.pekka.clients.fraud.FraudCheckResponse;
import com.pekka.clients.fraud.FraudClient;
import com.pekka.clients.notification.NotificationClient;
import com.pekka.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(
        CustomerRepository customerRepository,
        RestTemplate restTemplate,
        FraudClient fraudClient,
        NotificationClient notificationClient,
        RabbitMQMessageProducer rabbitMQMessageProducer
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

        // This is not needed thanks to our RabbitMQ
//        notificationClient.notifyCustomer(
//                new NotificationRequest(
//                    customer.getId(), customer.getEmail(), "Homo olet!"
//                )
//        );

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                "Homo olet!");

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
}
