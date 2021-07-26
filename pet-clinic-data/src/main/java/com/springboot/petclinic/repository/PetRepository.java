package com.springboot.petclinic.repository;

import com.springboot.petclinic.entity.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
