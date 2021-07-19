package com.springboot.petclinic.service;

import com.springboot.petclinic.entity.Pet;
import com.springboot.petclinic.entity.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
