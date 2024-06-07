package com.microservice.authentication.service.jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getToken(UserDetails user);
    String getUsernameFromToken(String token);
    boolean validateToken(String token, UserDetails userDetails);
    String getTokenFromRequest(HttpServletRequest request);
    Cookie createCookie(String token);
}
