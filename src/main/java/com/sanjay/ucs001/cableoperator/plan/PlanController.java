package com.sanjay.ucs001.cableoperator.plan;

import com.sanjay.ucs001.cableoperator.plan.dto.CreatePlanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/operator/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @GetMapping
    public String findAllPlans(Model model) {
        model.addAttribute("plans", this.planService.findAllPlans());
        return "plan/index";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
        model.addAttribute("plan", new Plan());
        return "plan/add";
    }

    @PostMapping("/add")
    public String addCustomer(CreatePlanRequest plan) {
        this.planService.createPlan(plan);
        return "redirect:/operator/plan/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        this.planService.deletePlan(id);
        return "redirect:/operator/plan";
    }

    @GetMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, Model model) {
        Optional<Plan> plan = this.planService.findPlan(id);
        if (plan.isEmpty()) {
            return "redirect:/operator/plan";
        }

        model.addAttribute("plan", plan.get());
        model.addAttribute("id", id);

        return "plan/update";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id, CreatePlanRequest plan) {
        this.planService.updatePlan(id, plan);
        return "redirect:/operator/plan/update/" + id;
    }

}