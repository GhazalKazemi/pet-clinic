package com.springboot.petclinic.service;

import com.springboot.petclinic.entity.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

}
