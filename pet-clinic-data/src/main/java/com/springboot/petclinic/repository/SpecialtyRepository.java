package com.springboot.petclinic.repository;

import com.springboot.petclinic.entity.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
