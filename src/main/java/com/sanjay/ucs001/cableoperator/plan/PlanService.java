package com.sanjay.ucs001.cableoperator.plan;

import com.sanjay.ucs001.cableoperator.plan.dto.CreatePlanRequest;

import java.util.List;
import java.util.Optional;

public interface PlanService {
    Plan createPlan(CreatePlanRequest createPlanRequest);

    List<Plan> findAllPlans();

    Optional<Plan> findPlan(Long id);

    void deletePlan(Long id);

    void updatePlan(Long id, Plan plan);
}
