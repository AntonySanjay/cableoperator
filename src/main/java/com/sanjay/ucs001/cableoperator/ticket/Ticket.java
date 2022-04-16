package com.sanjay.ucs001.cableoperator.ticket;

import com.sanjay.ucs001.cableoperator.common.BaseEntity;
import com.sanjay.ucs001.cableoperator.ticket.dto.CreateTicketRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class Ticket extends BaseEntity {
    private String subscriptionId;
    private String contactNumber;
    private String contactAddress;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String message;

    @Column(nullable = false)
    private Boolean isOpen = true;

    public Ticket(CreateTicketRequest request) {
        this.subscriptionId = request.getSubscriptionId();
        this.title = request.getTitle();
        this.message = request.getMessage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ticket ticket = (Ticket) o;
        return getId() != null && Objects.equals(getId(), ticket.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
