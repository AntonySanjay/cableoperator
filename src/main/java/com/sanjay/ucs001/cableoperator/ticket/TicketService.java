package com.sanjay.ucs001.cableoperator.ticket;

import com.sanjay.ucs001.cableoperator.ticket.dto.CreateTicketRequest;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> findAllTickets();

    Ticket createTicket(CreateTicketRequest request);

    Optional<Ticket> findTicketById(Long id);

    void markTicketAsDone(Ticket ticket);
}
