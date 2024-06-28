package com.microservice.tickets.service.ticket;

import com.microservice.tickets.service.dto.TicketsResponseDto;
import com.microservice.tickets.service.dto.TicketDto;
import com.microservice.tickets.service.dto.TicketToSaveDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    TicketsResponseDto listAllTickets();
    TicketsResponseDto searchTicket(String search, int page, int size);
    TicketDto createTicket(TicketToSaveDto ticketToSaveDto);
    List<TicketDto> listTicketsByStatus(String status);
    TicketDto getTicketById(UUID id);
    TicketDto updateTicket(UUID id, TicketToSaveDto ticketToSaveDto);
    void deleteTicket(UUID id);
}
