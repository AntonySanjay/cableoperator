package com.sanjay.ucs001.cableoperator.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
