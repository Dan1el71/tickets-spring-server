package com.microservice.authentication.controller;

import com.microservice.authentication.service.dto.AuthResponseDto;
import com.microservice.authentication.service.dto.LoginRequestDto;
import com.microservice.authentication.service.dto.RegisterRequestDto;
import com.microservice.authentication.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto request, HttpServletResponse response){
        AuthResponseDto token = authService.register(request);

        Cookie cookie = createAuthCookie(token.getToken());
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request,  HttpServletResponse response){
        AuthResponseDto token = authService.login(request);

        Cookie cookie = createAuthCookie(token.getToken());
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/guest")
    public ResponseEntity<AuthResponseDto> loginGuest(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok().build();
    }

    private Cookie createAuthCookie(String token) {
        Cookie cookie = new Cookie("auth", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 365); // 1 año
        return cookie;
    }
}
