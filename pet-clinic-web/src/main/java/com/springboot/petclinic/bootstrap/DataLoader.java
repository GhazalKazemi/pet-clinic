package com.springboot.petclinic.bootstrap;

import com.springboot.petclinic.entity.*;
import com.springboot.petclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetTypeService petTypeService;

    private final SpecialtyService specialtyService;

    private final VisitService visitService;
    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtyService specialtyService,
                      VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        petTypeService.save(dog);
        petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");

        specialtyService.save(radiology);
        specialtyService.save(dentistry);
        specialtyService.save(surgery);

        Owner james = new Owner();
        //james.setId(1L);
        james.setFirstName("James");
        james.setLastName("Doe");
        james.setAddress("123 Conlan St");
        james.setCity("Brisbane");
        james.setPhoneNumber("123 4567 89");



        Pet jamesPet = new Pet();
        jamesPet.setName("Mikey");
        jamesPet.setPetType(dog);
        jamesPet.setBirthDate(LocalDate.of(2019,10,12));
        jamesPet.setOwner(james);

        james.getPets().add(jamesPet);
        ownerService.save(james);

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

        Visit visit1 = new Visit();
        visit1.setDescription("Cat f3 vaccination");
        visit1.setPet(fionaPet);
        visit1.setDate(LocalDate.now());

        visitService.save(visit1);

        Vet vet1 = new Vet();
        // vet1.setId(1L);
        vet1.setFirstName("Mina");
        vet1.setLastName("Moradi");

        vet1.getSpecialties().add(radiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        // vet2.setId(2L);
        vet2.setFirstName("Anderson");
        vet2.setLastName("Times");
        vet2.getSpecialties().add(surgery);
        vetService.save(vet2);
    }
}
