package com.sanjay.ucs001.cableoperator.customer;


import com.sanjay.ucs001.cableoperator.common.LuhnAlgorithm;
import com.sanjay.ucs001.cableoperator.common.Utils;
import com.sanjay.ucs001.cableoperator.customer.dto.CreateCustomer;
import com.sanjay.ucs001.cableoperator.customer.dto.RechargeCardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final LuhnAlgorithm luhnAlgorithm;
    private final Utils utils;

    @GetMapping("/operator/customer")
    public String allCustomers(Model model) {
        model.addAttribute("customers", this.customerService.findAllCustomers());
        model.addAttribute("utils", utils);
        return "customer/index";
    }

    @GetMapping("/operator/customer/add")
    public String addCustomer(Model model) {
        Customer customer = this.customerService.getCustomerWithSubID();
        model.addAttribute("customer", customer);
        model.addAttribute("plan", this.customerService.getAllPlans());
        return "customer/add";
    }

    @PostMapping("/operator/customer/add")
    public String addCustomer(CreateCustomer customer) {
        this.customerService.createCustomer(customer);
        return "redirect:/operator/customer/";
    }

    @GetMapping("/operator/customer/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        this.customerService.deleteCustomer(id);
        return "redirect:/operator/customer";
    }

    @GetMapping("/operator/customer/update/{id}")
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

    @PostMapping("/operator/customer/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, CreateCustomer customer) {
        this.customerService.updateCustomer(id, customer);
        return "redirect:/operator/customer/update/" + id;
    }

    @GetMapping("/customer/{id}")
    public String customerPlan(@PathVariable("id") String id, Model model) {
        Optional<Customer> customer = this.customerService.findBySubId(id);

        if (customer.isEmpty()) {
            return "redirect:/404";
        }
        model.addAttribute("customer", customer.get());

        return "customer/plan/detail";
    }

    @GetMapping("/customer")
    public String getCustomerPlan() {
        return "customer/plan/index";
    }

    @PostMapping("/customer")
    public String getCustomerPlan(String id) {
        return "redirect:/customer/" + id;
    }

    @GetMapping("/customer/{id}/recharge")
    public String customerRecharge(@PathVariable("id") String id, Model model) {
        Optional<Customer> customer = this.customerService.findBySubId(id);
        if (customer.isEmpty()) {
            return "redirect:/404";
        }

        model.addAttribute("id", id);
        model.addAttribute("model", new RechargeCardRequest());

        return "customer/plan/recharge";
    }

    @GetMapping("/customer/invalid-transaction")
    public String customerInvalidTransaction() {
        return "customer/invalid-transaction";
    }

    @PostMapping("/customer/{id}/recharge")
    public String customerRecharge(@PathVariable("id") String id, RechargeCardRequest request) {
        boolean isValid = this.luhnAlgorithm.isCardValid(request.getCardNumber());
        if (!isValid) {
            return "redirect:/customer/invalid-transaction/";
        }

        this.customerService.increaseExpiryDateOneMonth(id);
        return "redirect:/customer/" + id;
    }

}
