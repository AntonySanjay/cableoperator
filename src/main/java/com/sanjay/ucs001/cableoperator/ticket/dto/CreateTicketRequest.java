package com.sanjay.ucs001.cableoperator.ticket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTicketRequest {
    private String subscriptionId;
    private String title;
    private String message;
}
