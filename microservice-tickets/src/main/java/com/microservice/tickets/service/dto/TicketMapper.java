package com.microservice.tickets.service.dto;

import com.microservice.tickets.persistence.model.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketDto toDto(Ticket ticket);
    Ticket toEntity(TicketToSaveDto ticketToSaveDto);
    Ticket toEntity(TicketDto ticketDto);
    TicketsResponseDto toTicketsResponseDto(Ticket ticket);
}
