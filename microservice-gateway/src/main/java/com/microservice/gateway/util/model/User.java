package com.microservice.gateway.util.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private UUID id;
    private String name;
    private String photo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
