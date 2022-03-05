package com.sanjay.ucs001.cableoperator.plan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePlanRequest {
    private String name;
    private int planPrice;
    private String features;
}
