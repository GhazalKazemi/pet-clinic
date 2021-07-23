package com.springboot.petclinic.bootstrap;

import com.springboot.petclinic.entity.Owner;
import com.springboot.petclinic.entity.Pet;
import com.springboot.petclinic.entity.PetType;
import com.springboot.petclinic.entity.Vet;
import com.springboot.petclinic.service.OwnerService;
import com.springboot.petclinic.service.PetTypeService;
import com.springboot.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetTypeService petTypeService;
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        petTypeService.save(dog);
        petTypeService.save(cat);

        Owner james = new Owner();
        //james.setId(1L);
        james.setFirstName("James");
        james.setLastName("Doe");
        james.setAddress("123 Conlan St");
        james.setCity("Brisbane");
        james.setPhoneNumber("123 4567 89");

        ownerService.save(james);

        Pet jamesPet = new Pet();
        jamesPet.setName("Mikey");
        jamesPet.setPetType(dog);
        jamesPet.setBirthDate(LocalDate.of(2019,10,12));
        jamesPet.setOwner(james);

        james.getPets().add(jamesPet);

        Owner fiona = new Owner();
        //fiona.setId(2L);
        fiona.setFirstName("Fiona");
        fiona.setLastName("Roberts");
        fiona.setAddress("321 Elizabeth Street");
        fiona.setCity("Melbourne");
        fiona.setPhoneNumber("987 6543 21");

        Pet fionaPet = new Pet();
        fionaPet.setName("Rosco");
        fionaPet.setPetType(cat);
        fionaPet.setOwner(fiona);
        fionaPet.setBirthDate(LocalDate.of(2021, 01, 12));

        fiona.getPets().add(fionaPet);
        ownerService.save(fiona);

        Vet vet1 = new Vet();
       // vet1.setId(1L);
        vet1.setFirstName("Mina");
        vet1.setLastName("Moradi");

        vetService.save(vet1);

        Vet vet2 = new Vet();
       // vet2.setId(2L);
        vet2.setFirstName("Anderson");
        vet2.setLastName("Times");

        vetService.save(vet2);
    }
}
