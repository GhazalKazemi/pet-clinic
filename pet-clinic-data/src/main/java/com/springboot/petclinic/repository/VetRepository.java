package com.springboot.petclinic.repository;

import com.springboot.petclinic.entity.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
