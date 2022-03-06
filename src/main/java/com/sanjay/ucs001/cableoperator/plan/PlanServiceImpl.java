package com.sanjay.ucs001.cableoperator.plan;

import com.sanjay.ucs001.cableoperator.plan.dto.CreatePlanRequest;
import com.sanjay.ucs001.cableoperator.plan.exception.PlanNotFoundException;
import com.sanjay.ucs001.cableoperator.plan.models.Feature;
import com.sanjay.ucs001.cableoperator.plan.models.Plan;
import com.sanjay.ucs001.cableoperator.plan.repository.FeatureRepository;
import com.sanjay.ucs001.cableoperator.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final FeatureRepository featureRepository;

    @Override
    public Plan createPlan(CreatePlanRequest createPlanRequest) {
        Plan plan = new Plan(createPlanRequest);
        Arrays.stream(createPlanRequest.getFeatures().split(","))
                .forEach(v -> {
                    plan.addFeature(new Feature(v));
                });
        return this.planRepository.save(plan);
    }

    private List<Feature> generateFeatures(String values) {
        return Arrays.stream(values.split(","))
                .map(Feature::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Plan> findAllPlans() {
        return this.planRepository.findAll();
    }

    @Override
    public Optional<Plan> findPlan(Long id) {
        return this.planRepository.findById(id);
    }

    @Override
    public void deletePlan(Long id) {
        Plan plan = this.planRepository.findById(id)
                .orElseThrow(() -> new PlanNotFoundException("The plan is not found"));
        this.planRepository.delete(plan);
    }

    @Override
    public void updatePlan(Long id, CreatePlanRequest createPlanRequest) {
        Plan plan = new Plan(createPlanRequest);
        plan.setId(id);
        this.planRepository.save(plan);
    }
}
