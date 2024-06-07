package com.microservice.authentication.controller;

import com.microservice.authentication.service.auth.AuthService;
import com.microservice.authentication.persistence.dto.account.AccountResponseDto;
import com.microservice.authentication.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/account")
public class AccountController {
    private final JwtService jwtService;
    private final AuthService authService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDetails> getByUsername(@PathVariable("username")  String username) {
        UserDetails account = authService.loadUserByUsername(username);
        if(account== null) throw new UsernameNotFoundException("User not found");
        return ResponseEntity.ok(account);
    }

    @GetMapping()
    public ResponseEntity<AccountResponseDto> getAccount(HttpServletRequest request) {
        String token = jwtService.getTokenFromRequest(request);
        AccountResponseDto account = authService.verify(token);
        return ResponseEntity.ok(account);
    }
}
