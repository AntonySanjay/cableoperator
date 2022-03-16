package com.sanjay.ucs001.cableoperator.customer;

import com.sanjay.ucs001.cableoperator.customer.dto.CreateCustomer;
import com.sanjay.ucs001.cableoperator.customer.exceptions.CustomerNotFoundException;
import com.sanjay.ucs001.cableoperator.plan.Plan;
import com.sanjay.ucs001.cableoperator.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PlanRepository planRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Plan> getAllPlans() {
        return this.planRepository.findAll();
    }

    @Override
    public Optional<Customer> findBySubId(String subId) {
        return this.customerRepository.findBySubscriptionId(subId);
    }

    @Override
    public Optional<Customer> findOneById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public void createCustomer(CreateCustomer createCustomer) {
        Customer customer = new Customer(createCustomer);
        customer.setPlanExpiresAt(LocalDate.now().plusMonths(1));
        this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        this.customerRepository.delete(customer);
    }

    @Override
    public void updateCustomer(Long id, CreateCustomer createCustomer) {
        Customer customer = this.findOneById(id).orElseThrow(() -> new CustomerNotFoundException("Customer Not found"));
        customer.update(createCustomer);
        this.customerRepository.save(customer);
    }

    // FIXME: Improve this method
    // currently when the user pays before the plan is finished the days available for the user is removed
    @Override
    public void increaseExpiryDateOneMonth(String subscriptionId) {
        Customer customer = this.customerRepository.findBySubscriptionId(subscriptionId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with the given subscription ID not found"));
        customer.setPlanExpiresAt(LocalDate.now().plusMonths(1));
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

    @Override
    public Long customerCount() {
        return this.customerRepository.count();
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
