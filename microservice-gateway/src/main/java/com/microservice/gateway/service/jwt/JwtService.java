package com.microservice.gateway.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    boolean validateToken(String token, UserDetails userDetails);
    String getUsernameFromToken(String token);
}
