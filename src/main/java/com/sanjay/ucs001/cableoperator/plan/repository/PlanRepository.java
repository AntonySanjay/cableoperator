package com.sanjay.ucs001.cableoperator.plan.repository;

import com.sanjay.ucs001.cableoperator.plan.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
