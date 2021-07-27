package com.springboot.petclinic.repository;


import com.springboot.petclinic.entity.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
