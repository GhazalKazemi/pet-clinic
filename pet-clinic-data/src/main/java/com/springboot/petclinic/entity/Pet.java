package com.springboot.petclinic.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "pet")
public class Pet extends BaseEntity{
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "pet_type_id")
    @Column(name = "pet_type")
    private PetType petType;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @Column(name = "owner")
    private Owner owner;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
