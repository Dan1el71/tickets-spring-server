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
    public TicketsResponseDto searchTicket(String search, int page, int size) {
        List<Ticket> tickets = ticketRepository.findByReasonContainingIgnoreCase(search);

        long totalTickets = tickets.size();

        if (totalTickets == 0) {
            throw new ItemNotFoundException("No tickets found");
        }

        List<TicketDto> ticketDto = paginateTickets(tickets, page, size).stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());

        return new TicketsResponseDto(ticketDto, totalTickets);
    }

    private List<Ticket> paginateTickets(List<Ticket> tickets, int page, int size) {
        return tickets.stream()
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
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
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setReason(ticketToSaveDto.reason());
            ticket.setDescription(ticketToSaveDto.description());
            ticket.setPriority(ticketToSaveDto.priority());
            ticket.setStatus(ticketToSaveDto.status());
            ticket.setAssigneeId(ticketToSaveDto.assigneeId());
            ticket.setUserId(ticketToSaveDto.userId());
            ticket.setComments(ticketToSaveDto.comments());
            ticket.setQualification(ticketToSaveDto.qualification());

            Ticket updatedTicket = ticketRepository.save(ticket);
            return ticketMapper.toDto(updatedTicket);
        }).orElseThrow(() -> new ItemNotFoundException("Ticket not found"));
    }

    @Override
    public void deleteTicket(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Ticket not found"));

        ticketRepository.delete(ticket);
    }
}
