package com.microservice.authentication.controller;

import com.microservice.authentication.service.auth.AuthService;
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
    private final AuthService authService;

    @GetMapping("/{username}")
    public ResponseEntity<UserDetails> getByUsername(@PathVariable("username")  String username) {
        UserDetails account = authService.loadUserByUsername(username);
        if(account== null) throw new UsernameNotFoundException("User not found");
        return ResponseEntity.ok(account);
    }
}
