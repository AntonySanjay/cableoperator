package com.sanjay.ucs001.cableoperator.plan;

import com.sanjay.ucs001.cableoperator.common.BaseEntity;
import com.sanjay.ucs001.cableoperator.customer.Customer;
import com.sanjay.ucs001.cableoperator.plan.dto.CreatePlanRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Plan extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String features;

    @OneToMany(
            mappedBy = "plan"
    )
    @ToString.Exclude
    private List<Customer> customers = new ArrayList<>();

    public Plan(CreatePlanRequest plan) {
        this.name = plan.getName();
        this.price = plan.getPlanPrice();
        this.features = plan.getFeatures();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Plan plan = (Plan) o;
        return getId() != null && Objects.equals(getId(), plan.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
