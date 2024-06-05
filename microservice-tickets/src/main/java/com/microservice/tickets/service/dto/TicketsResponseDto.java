package com.microservice.tickets.service.dto;

import java.util.List;

public record TicketsResponseDto(
    List<TicketDto> tickets,
    Long total
) {
}
