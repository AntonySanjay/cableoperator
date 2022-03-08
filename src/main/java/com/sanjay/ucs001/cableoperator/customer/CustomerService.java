package com.sanjay.ucs001.cableoperator.customer;

import com.sanjay.ucs001.cableoperator.customer.dto.CreateCustomer;
import com.sanjay.ucs001.cableoperator.plan.Plan;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAllCustomers();

    List<Plan> getAllPlans();

    Optional<Customer> findBySubId(String subId);

    Optional<Customer> findOneById(Long id);

    Customer createCustomer(CreateCustomer customer);

    void deleteCustomer(Long id);

    void updateCustomer(Long id, CreateCustomer customer);

    void increaseExpiryDateOneMonth(String subscriptionId);

    Customer getCustomerWithSubID();
}
