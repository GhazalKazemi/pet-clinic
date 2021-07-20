package com.springboot.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class VetController {

    @GetMapping({"/vets","/vets/index"})
    public String listVets(){
        return "vet/index";
    }
}
