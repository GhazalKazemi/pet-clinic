package com.springboot.petclinic.controller;

import com.springboot.petclinic.entity.Owner;
import com.springboot.petclinic.entity.Pet;
import com.springboot.petclinic.entity.PetType;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    @Mock
    PetService petService;
    @InjectMocks
    VisitController visitController;
    MockMvc mockMvc;
    private final UriTemplate visitUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String > uriVariables = new HashMap<>();
    private URI visitUri;
    @BeforeEach
    void setUp() {
        Long ownerId = 1L;
        Long petId = 1L;
        when(petService.findById(anyLong()))
                .thenReturn( Pet.builder()
                .id(petId)
                .birthDate(LocalDate.of(2018,11,11))
                .name("Jasper")
                .owner(Owner.builder()
                        .id(ownerId)
                        .firstName("Jane")
                        .lastName("Public")
                        .build())
                .petType(PetType.builder()
                        .name("Dog")
                        .build())
                .build());
        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitUri = visitUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitUri))
                .andExpect(status().isOk())
                .andExpect(view().name("pet/createOrUpdateVisitForm"));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("date", "2018-11-11")
                .param("description", "Yet another description")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"))
                .andExpect(model().attributeExists("visit"));
    }
}