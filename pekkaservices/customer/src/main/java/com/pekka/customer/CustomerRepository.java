package com.pekka.customer;

import org.springframework.data.jpa.repository.JpaRepository;

// This will be injected inside our CustomerService class
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
