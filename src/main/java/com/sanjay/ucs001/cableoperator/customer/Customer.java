package com.sanjay.ucs001.cableoperator.customer;

import com.sanjay.ucs001.cableoperator.common.BaseEntity;
import com.sanjay.ucs001.cableoperator.customer.dto.CreateCustomer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String subscriptionId;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String contactAddress;

    @Column(nullable = false)
    private String email;

    public Customer(CreateCustomer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.subscriptionId = customer.getSubscriptionId();
        this.contactAddress = customer.getContactAddress();
        this.contactNumber = customer.getContactNumber();
        this.email = customer.getEmail();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return getId() != null && Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
