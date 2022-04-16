package com.sanjay.ucs001.cableoperator.ticket;

import com.sanjay.ucs001.cableoperator.customer.Customer;
import com.sanjay.ucs001.cableoperator.customer.CustomerService;
import com.sanjay.ucs001.cableoperator.ticket.dto.CreateTicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final CustomerService customerService;
    private final TicketService ticketService;

    @GetMapping("/customer/{id}/help")
    public String createTicket(@PathVariable("id") String id, Model model) {
        Optional<Customer> customer = this.customerService.findBySubId(id);
        if (customer.isEmpty()) {
            return "redirect:/404";
        }

        model.addAttribute("ticket", new CreateTicketRequest());
        model.addAttribute("userId", id);

        return "ticket/customer";
    }

    @PostMapping("/customer/{id}/help")
    public String createTicket(@PathVariable("id") String id, CreateTicketRequest request) {
        Optional<Customer> customer = this.customerService.findBySubId(id);
        if (customer.isEmpty()) {
            return "redirect:/404";
        }

        this.ticketService.createTicket(request);

        return "redirect:/customer/" + id;
    }

    @GetMapping("/operator/ticket")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", this.ticketService.findAllTickets());
        model.addAttribute("ticketCount", this.ticketService.openTicketCount());

        return "ticket/all-tickets";
    }

    @GetMapping("/operator/ticket/{id}")
    public String getTicket(@PathVariable("id") Long id, Model model) {
        Optional<Ticket> ticket = this.ticketService.findTicketById(id);
        if (ticket.isEmpty()) {
            return "redirect:/operator/ticket";
        }
        Ticket currentTicket = ticket.get();
        Customer customer = customerService.findBySubId(currentTicket.getSubscriptionId())
                .orElseGet(
                        () -> {
                            Customer c = new Customer();
                            c.setContactAddress(currentTicket.getContactAddress());
                            c.setContactNumber(currentTicket.getContactNumber());
                            return c;
                        }
                );

        model.addAttribute("ticket", currentTicket);
        model.addAttribute("customer", customer);

        return "ticket/details";
    }

    @GetMapping("/operator/ticket/{id}/done")
    public String markTicketAsDone(@PathVariable("id") Long id) {
        Optional<Ticket> ticket = this.ticketService.findTicketById(id);
        if (ticket.isEmpty()) {
            return "redirect:/operator/ticket";
        }

        this.ticketService.markTicketAsDone(ticket.get());

        return "redirect:/operator/ticket/" + id;
    }

}
