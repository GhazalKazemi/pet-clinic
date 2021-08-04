package com.springboot.petclinic.controller;

import com.springboot.petclinic.entity.Owner;
import com.springboot.petclinic.entity.Pet;
import com.springboot.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owner/ownerDetails");
        Owner owner = ownerService.findById(ownerId);

        modelAndView.addObject(owner);
        return modelAndView;
    }
}
