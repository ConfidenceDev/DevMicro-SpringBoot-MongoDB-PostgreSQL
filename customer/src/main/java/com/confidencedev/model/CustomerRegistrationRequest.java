package com.confidencedev.model;

public record CustomerRegistrationRequest(
        String fullName,
        String email) {
}
