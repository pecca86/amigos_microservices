package com.pekka.customer;

// A Record implements the basic class methods (setters, getters, equals, hashCode, toString) "for free"
public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
