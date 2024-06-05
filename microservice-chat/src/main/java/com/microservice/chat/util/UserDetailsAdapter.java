package com.microservice.chat.util;

import com.microservice.chat.util.model.Authorities;
import com.microservice.chat.util.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsAdapter  {
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
}
