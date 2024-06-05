package com.microservice.tickets.service.dto;

import com.microservice.tickets.persistence.model.Priority;
import com.microservice.tickets.persistence.model.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketDto (
    UUID id,
    String reason,
    String description,
    UUID assignee,
    UUID userId,
    Status status,
    Priority priority,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
){
}
