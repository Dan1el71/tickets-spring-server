package com.microservice.authentication.controller;

import com.microservice.authentication.persistence.dto.AuthResponseDto;
import com.microservice.authentication.persistence.dto.LoginRequestDto;
import com.microservice.authentication.persistence.dto.RegisterRequestDto;
import com.microservice.authentication.service.auth.AuthService;
import com.microservice.authentication.service.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request, HttpServletResponse response){
        AuthResponseDto token = authService.register(request);

        Cookie cookie = jwtService.createCookie(token.getToken());
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request,  HttpServletResponse response){
        AuthResponseDto token = authService.login(request);

        Cookie cookie = jwtService.createCookie(token.getToken());
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
