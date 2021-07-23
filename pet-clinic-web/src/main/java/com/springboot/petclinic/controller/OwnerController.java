package com.springboot.petclinic.controller;

import com.springboot.petclinic.entity.Owner;
import com.springboot.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping({"", "/", "/index"})
    public String showOwners(Model model){
        Set<Owner> owners = ownerService.findAll();
        model.addAttribute("owners", owners);
        return "owner/index";
    }
    @GetMapping("/find")
    public String findOwners(){
        return "not-implemented";
    }
}
