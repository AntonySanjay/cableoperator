package com.sanjay.ucs001.cableoperator.plan.models;


import com.sanjay.ucs001.cableoperator.common.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Feature extends BaseEntity {

    @Column(nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public Feature(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return Objects.equals(value, feature.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
