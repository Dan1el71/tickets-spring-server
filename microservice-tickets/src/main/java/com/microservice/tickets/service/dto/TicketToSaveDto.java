package com.microservice.tickets.service.dto;

import com.microservice.tickets.persistence.model.Priority;
import com.microservice.tickets.persistence.model.Status;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TicketToSaveDto(
        @NotNull
        String reason,
        @NotNull
        String description,
        UUID assigneeId,
        @NotNull
        UUID userId,
        Status status,
        Priority priority,
        String comments,
        Integer qualification
) {
}
