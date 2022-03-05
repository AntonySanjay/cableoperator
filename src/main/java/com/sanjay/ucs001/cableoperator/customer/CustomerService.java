package com.sanjay.ucs001.cableoperator.customer;

import com.sanjay.ucs001.cableoperator.customer.dto.CreateCustomer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAllCustomers();

    Optional<Customer> findOneById(Long id);

    Customer createCustomer(CreateCustomer customer);

    void deleteCustomer(Long id);

    void updateCustomer(Long id, CreateCustomer customer);

    Customer getCustomerWithSubID();
}
