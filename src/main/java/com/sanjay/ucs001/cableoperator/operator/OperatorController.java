package com.sanjay.ucs001.cableoperator.operator;

import com.sanjay.ucs001.cableoperator.customer.CustomerService;
import com.sanjay.ucs001.cableoperator.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OperatorController {

    private final CustomerService customerService;
    private final TicketService ticketService;

    @GetMapping("/operator")
    public String operator(Model model) {
        model.addAttribute("customerCount", this.customerService.customerCount());
        model.addAttribute("ticketCount", this.ticketService.openTicketCount());
        return "operator";
    }
}
