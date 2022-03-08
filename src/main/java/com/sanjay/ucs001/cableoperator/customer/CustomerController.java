package com.sanjay.ucs001.cableoperator.customer;


import com.sanjay.ucs001.cableoperator.customer.dto.CreateCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/operator/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String allCustomers(Model model) {
        model.addAttribute("customers", this.customerService.findAllCustomers());
        return "customer/index";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
        Customer customer = this.customerService.getCustomerWithSubID();
        model.addAttribute("customer", customer);
        model.addAttribute("plan", this.customerService.getAllPlans());
        return "customer/add";
    }

    @PostMapping("/add")
    public String addCustomer(CreateCustomer customer) {
        this.customerService.createCustomer(customer);
        return "redirect:/operator/customer/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        this.customerService.deleteCustomer(id);
        return "redirect:/operator/customer";
    }

    @GetMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, Model model) {
        Optional<Customer> customer = this.customerService.findOneById(id);
        if (customer.isEmpty()) {
            return "redirect:operator/customer";
        }

        model.addAttribute("customer", customer.get());
        model.addAttribute("id", id);
        model.addAttribute("allPlans", this.customerService.getAllPlans());

        return "customer/update";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, CreateCustomer customer) {
        this.customerService.updateCustomer(id, customer);
        return "redirect:/operator/customer/update/" + id;
    }

}
