package com.microservice.tickets.controller;


import com.microservice.tickets.service.dto.TicketsResponseDto;
import com.microservice.tickets.service.dto.TicketDto;
import com.microservice.tickets.service.dto.TicketToSaveDto;
import com.microservice.tickets.service.ticket.TicketService;
import jakarta.validation.Valid;
import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody TicketToSaveDto ticket) {
        TicketDto savedTicket = ticketService.createTicket(ticket);
        return ResponseEntity.ok(savedTicket);
    }

    @GetMapping
    public ResponseEntity<TicketsResponseDto> AllTickets(){
        return ResponseEntity.ok(ticketService.listAllTickets());
    }

    @GetMapping("/status")
    public ResponseEntity<List<TicketDto>> TicketsByStatus(@RequestParam String status){
        return ResponseEntity.ok(ticketService.listTicketsByStatus(status));
    }

    @GetMapping("/search?reason={search}")
    public ResponseEntity<String> TicketsByReason(@PathVariable("search") String search){
        return ResponseEntity.ok("Not implemented");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> TicketById(@PathVariable UUID id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable UUID id, @Valid @RequestBody TicketToSaveDto ticket){
        return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id){
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }
}
