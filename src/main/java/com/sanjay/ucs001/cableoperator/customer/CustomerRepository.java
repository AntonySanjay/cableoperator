package com.sanjay.ucs001.cableoperator.customer;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findBySubscriptionId(String subId);

}
