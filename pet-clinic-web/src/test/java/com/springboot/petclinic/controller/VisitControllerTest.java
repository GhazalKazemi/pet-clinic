package com.springboot.petclinic.controller;

import com.springboot.petclinic.entity.Owner;
import com.springboot.petclinic.entity.Pet;
import com.springboot.petclinic.entity.Visit;
import com.springboot.petclinic.service.OwnerService;
import com.springboot.petclinic.service.PetService;
import com.springboot.petclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    @Mock
    PetService petService;
    @Mock
    VisitService visitService;
    @Mock
    OwnerService ownerService;
    @InjectMocks
    VisitController visitController;
    MockMvc mockMvc;
    Owner owner;
    Pet pet;
    Set<Visit> visits;
    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();
        pet = Pet.builder().id(2L).build();
        visits = new HashSet<>();
        visits.add(Visit.builder().id(1L).description("checkup").build());
        visits.add(Visit.builder().id(2L).description("checkup 2").build());
        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initNewVisitForm() throws Exception {
    }

    @Test
    void processNewVisitForm() {
    }
}