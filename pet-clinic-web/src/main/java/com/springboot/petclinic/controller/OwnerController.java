package com.springboot.petclinic.controller;

import com.springboot.petclinic.entity.Owner;
import com.springboot.petclinic.entity.Pet;
import com.springboot.petclinic.service.OwnerService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
//    @GetMapping({"", "/", "/index"})
//    public String showOwners(Model model){
//        Set<Owner> owners = ownerService.findAll();
//        model.addAttribute("owners", owners);
//        return "owner/index";
//    }
    @GetMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owner/findOwners";
    }
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        // find owners by last name
        List<Owner> owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (owners.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owner/findOwners";
        }
        else if (owners.size() == 1) {
            // 1 owner found
            owner = owners.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }
        else {
            // multiple owners found
            model.addAttribute("owners", owners);
            return "owner/ownersList";
        }
    }
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView modelAndView = new ModelAndView("owner/ownerDetails");
        Owner owner = ownerService.findById(ownerId);

        modelAndView.addObject(owner);
        return modelAndView;
    }
    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owner/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm( Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owner/createOrUpdateOwnerForm";
        }
        else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        model.addAttribute(ownerService.findById(ownerId));
        return "owner/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm( Owner owner, BindingResult result,
                                         @PathVariable Long ownerId) {
        if (result.hasErrors()) {
            return "owner/createOrUpdateOwnerForm";
        }
        else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
