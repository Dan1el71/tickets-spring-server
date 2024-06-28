package com.microservice.tickets.controller;


import com.microservice.tickets.service.dto.TicketsResponseDto;
import com.microservice.tickets.service.dto.TicketDto;
import com.microservice.tickets.service.dto.TicketToSaveDto;
import com.microservice.tickets.service.ticket.TicketService;
import jakarta.validation.Valid;
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

    @GetMapping("/search")
    public ResponseEntity<TicketsResponseDto> TicketsByReason(
            @RequestParam(value = "reason",required = false) String reason,
            @RequestParam(value = "page",defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
            ){
        return ResponseEntity.ok(ticketService.searchTicket(reason,page,size)) ;
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
