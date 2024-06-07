package com.microservice.authentication.service.auth;

import com.microservice.authentication.persistence.dto.account.AccountResponseDto;
import com.microservice.authentication.persistence.dto.AuthResponseDto;
import com.microservice.authentication.persistence.dto.LoginRequestDto;
import com.microservice.authentication.persistence.dto.RegisterRequestDto;
import org.springframework.security.core.userdetails.UserDetails;


public interface AuthService {
    AuthResponseDto login(LoginRequestDto request);
    AuthResponseDto register(RegisterRequestDto request);
    UserDetails loadUserByUsername(String username);
    AccountResponseDto verify(String token);
}
