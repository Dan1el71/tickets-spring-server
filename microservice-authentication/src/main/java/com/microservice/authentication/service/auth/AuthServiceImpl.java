package com.microservice.authentication.service.auth;

import com.microservice.authentication.exception.ResourceNotFoundException;
import com.microservice.authentication.exception.UnauthorizedException;
import com.microservice.authentication.exception.UserAlreadyExistsException;
import com.microservice.authentication.persistence.model.Account;
import com.microservice.authentication.persistence.model.Role;
import com.microservice.authentication.persistence.model.User;
import com.microservice.authentication.persistence.repository.AccountRepository;
import com.microservice.authentication.persistence.repository.UserRepository;
import com.microservice.authentication.persistence.dto.account.AccountMapper;
import com.microservice.authentication.persistence.dto.account.AccountResponseDto;
import com.microservice.authentication.persistence.dto.AuthResponseDto;
import com.microservice.authentication.persistence.dto.LoginRequestDto;
import com.microservice.authentication.persistence.dto.RegisterRequestDto;
import com.microservice.authentication.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        UserDetails user = accountRepository.findByUsername(
                request.getUsername()
        ).orElseThrow(() -> new UnauthorizedException("Invalid Credentials"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token = jwtService.getToken(user);

        return AuthResponseDto.builder().token(token).build();
    }

    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        accountRepository.findByUsername(request.getUsername()).ifPresent(
                account -> {
                    throw new UserAlreadyExistsException("User already exists");
                }
        );

        User user = User.builder()
                .name(request.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Account account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .isVerified(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .provider("Email")
                .userId(user)
                .role(Role.User)
                .build();

        userRepository.save(user);
        accountRepository.save(account);

        return AuthResponseDto.
                builder().
                token(jwtService.getToken(account)).
                build();
    }

    @Override
    public Account loadUserByUsername(String username) {
        return accountRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public AccountResponseDto verify(String token) {
        String username = jwtService.getUsernameFromToken(token);
        Account account = loadUserByUsername(username);

        if (account == null) {
            throw new ResourceNotFoundException("User not found");
        }

        return accountMapper.toAccountResponseDto(account);
    }
}
