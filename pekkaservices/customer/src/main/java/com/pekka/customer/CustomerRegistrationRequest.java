package com.pekka.customer;

// A Record implements the basic class methods "for free"
public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
