package com.springboot.petclinic.service;

import com.springboot.petclinic.entity.Owner;
import com.springboot.petclinic.entity.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
