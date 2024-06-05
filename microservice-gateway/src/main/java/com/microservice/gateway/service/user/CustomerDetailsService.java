package com.microservice.gateway.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.gateway.util.UserDetailsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerDetailsService implements UserDetailsService {
    private final RestTemplate template;
    private final ObjectMapper objectMapper;

    @Value("${account.service.url}")
    private String userServiceUrl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return objectMapper.readValue(
                    template.getForObject(
                            userServiceUrl + username, String.class), UserDetailsAdapter.class);
        } catch (JsonProcessingException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
