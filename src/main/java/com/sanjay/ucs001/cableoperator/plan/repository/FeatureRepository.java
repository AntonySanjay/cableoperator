package com.sanjay.ucs001.cableoperator.plan.repository;

import com.sanjay.ucs001.cableoperator.plan.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {
}
