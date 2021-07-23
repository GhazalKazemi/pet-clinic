package com.springboot.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {
    @GetMapping({"/","","index","index.html"})
    public String showHomepage(){
        return "index";
    }
    @GetMapping("/oups")
    public String showErrors(){
        return "not-implemented";
    }
}
