package com.pekka.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // For logging (lombok)
@RestController
@RequestMapping("api/v1/customers")
// Record makes this entity a read only object after creation
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("New customer registration: {}", customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }

}
