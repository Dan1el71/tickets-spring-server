package com.microservice.authentication.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getToken(UserDetails user);
    String getUsernameFromToken(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
