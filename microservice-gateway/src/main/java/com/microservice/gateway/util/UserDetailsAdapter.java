package com.microservice.gateway.util;

import com.microservice.gateway.util.model.Authorities;
import com.microservice.gateway.util.model.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsAdapter implements UserDetails {
    private String username;
    private String password;
    private User userId;
    private boolean enabled;
    private boolean isVerified;
    private String provider;
    private String providerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Authorities> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
