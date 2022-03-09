package com.sanjay.ucs001.cableoperator.ticket;

import com.sanjay.ucs001.cableoperator.ticket.dto.CreateTicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> findAllTickets() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket createTicket(CreateTicketRequest request) {
        Ticket ticket = new Ticket(request);
        return this.ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> findTicketById(Long id) {
        return this.ticketRepository.findById(id);
    }

    @Override
    public void markTicketAsDone(Ticket ticket) {
        ticket.setIsOpen(false);
        this.ticketRepository.save(ticket);
    }

}
