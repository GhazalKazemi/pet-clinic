package com.springboot.petclinic.repository;

import com.springboot.petclinic.entity.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
