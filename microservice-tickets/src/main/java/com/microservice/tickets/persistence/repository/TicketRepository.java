package com.microservice.tickets.persistence.repository;

import com.microservice.tickets.persistence.model.Status;
import com.microservice.tickets.persistence.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findByStatus(Status status);
    List<Ticket> findByReasonContainingIgnoreCase(String reason);
}
