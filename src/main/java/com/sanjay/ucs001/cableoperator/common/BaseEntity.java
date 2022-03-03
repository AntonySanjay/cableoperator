package com.sanjay.ucs001.cableoperator.common;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {
    @Id
    private Long id;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
