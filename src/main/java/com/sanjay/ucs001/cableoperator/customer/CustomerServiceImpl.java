package com.sanjay.ucs001.cableoperator.customer;

import com.sanjay.ucs001.cableoperator.customer.dto.CreateCustomer;
import com.sanjay.ucs001.cableoperator.customer.exceptions.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findOneById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(CreateCustomer createCustomer) {
        Customer customer = new Customer(createCustomer);
        return this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        this.customerRepository.delete(customer);
    }

    @Override
    public void updateCustomer(Long id, CreateCustomer createCustomer) {
        Customer customer = new Customer(createCustomer);
        customer.setId(id);
        this.customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerWithSubID() {
        Customer customer = new Customer();
        try {
            customer.setSubscriptionId(this.getSubId());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return customer;
    }


    // Checks whether the id is present in the database if present it will
    // generate new id or else returns the id
    private String getSubId() throws NoSuchAlgorithmException {
        String id = this.generateId();
        Optional<Customer> customer = this.customerRepository.findBySubscriptionId(id);
        if (customer.isPresent()) {
            return getSubId();
        }
        return id;
    }

    // Generates a random ID
    private String generateId() throws NoSuchAlgorithmException {
        Random random = SecureRandom.getInstanceStrong();
        int value = random.nextInt(Integer.MAX_VALUE);
        return String.valueOf(value);
    }
}
