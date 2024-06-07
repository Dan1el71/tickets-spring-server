package com.microservice.authentication.persistence.dto.account;

public record AccountResponseDto(
    String username,
    String email,
    String role,
    boolean isVerified
) {
}
