package com.microservice.authentication.service.auth;

import com.microservice.authentication.service.dto.AuthResponseDto;
import com.microservice.authentication.service.dto.LoginRequestDto;
import com.microservice.authentication.service.dto.RegisterRequestDto;
import org.springframework.security.core.userdetails.UserDetails;


public interface AuthService {
    AuthResponseDto login(LoginRequestDto request);
    AuthResponseDto register(RegisterRequestDto request);
    AuthResponseDto loginAsGuest();
    UserDetails loadUserByUsername(String username);
}
