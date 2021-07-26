package com.springboot.petclinic.repository;

import com.springboot.petclinic.entity.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
