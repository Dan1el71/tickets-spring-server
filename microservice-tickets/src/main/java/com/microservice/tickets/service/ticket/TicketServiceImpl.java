package com.microservice.tickets.service.ticket;

import com.microservice.tickets.exception.InvalidStatusException;
import com.microservice.tickets.exception.ItemNotFoundException;
import com.microservice.tickets.exception.NotAbleToSaveException;
import com.microservice.tickets.exception.ResourceNotFoundException;
import com.microservice.tickets.persistence.model.Priority;
import com.microservice.tickets.persistence.model.Status;
import com.microservice.tickets.persistence.model.Ticket;
import com.microservice.tickets.persistence.repository.TicketRepository;
import com.microservice.tickets.service.dto.TicketsResponseDto;
import com.microservice.tickets.service.dto.TicketDto;
import com.microservice.tickets.service.dto.TicketMapper;
import com.microservice.tickets.service.dto.TicketToSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketsResponseDto listAllTickets() {
        long totalTickets = ticketRepository.count();
        if (totalTickets == 0) {
            throw new ResourceNotFoundException("No tickets found");
        }

        return new TicketsResponseDto(ticketRepository.findAll()
                .stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList()), totalTickets);
    }

    @Override
    public List<TicketDto> searchTicketsByReason(String search) {
        return List.of();
    }

    @Override
    public TicketDto createTicket(TicketToSaveDto ticketDto) {
        try {
            Ticket ticket = ticketMapper.toEntity(ticketDto);
            ticket.setStatus(Status.OPEN);
            ticket.setPriority(Priority.PENDING);
            Ticket savedTicket = ticketRepository.save(ticket);
            return ticketMapper.toDto(savedTicket);
        } catch (Exception e) {
            throw new NotAbleToSaveException("Error creating ticket");
        }
    }

    @Override
    public List<TicketDto> listTicketsByStatus(String status) {
        try {
            Status state = Status.valueOf(status.toUpperCase());
            return ticketRepository.findByStatus(state)
                    .stream()
                    .map(ticketMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InvalidStatusException("Invalid status: " + status);
        }
    }

    @Override
    public TicketDto getTicketById(UUID id) {
        return ticketRepository.findById(id)
                .map(ticketMapper::toDto)
                .orElseThrow(() -> new ItemNotFoundException("Ticket not found"));
    }

    @Override
    @Transactional
    public TicketDto updateTicket(UUID id, TicketToSaveDto ticketToSaveDto) {
        ticketRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Ticket not found"));

        Ticket updatedTicket = ticketMapper.toEntity(ticketToSaveDto);
        Ticket savedTicket = ticketRepository.save(updatedTicket);

        return ticketMapper.toDto(savedTicket);
    }

    @Override
    public void deleteTicket(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Ticket not found"));

        ticketRepository.delete(ticket);
    }
}
