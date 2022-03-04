package com.sanjay.ucs001.cableoperator.operator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OperatorController {

    @GetMapping("/operator")
    public String admin() {
        return "operator";
    }
}
