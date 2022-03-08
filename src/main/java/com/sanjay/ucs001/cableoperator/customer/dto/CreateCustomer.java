package com.sanjay.ucs001.cableoperator.customer.dto;

import com.sanjay.ucs001.cableoperator.plan.Plan;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CreateCustomer {
    private String firstName;
    private String lastName;
    private String subscriptionId;
    private String contactNumber;
    private String contactAddress;
    private String email;
    private Plan plan;
}
