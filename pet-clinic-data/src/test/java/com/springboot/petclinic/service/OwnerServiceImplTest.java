package com.springboot.petclinic.service;

import com.springboot.petclinic.entity.Owner;
import com.springboot.petclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerServiceImpl ownerService;
    private static final String LAST_NAME = "Smith";
    private static final Long OWNER_ID = 1L;
    Owner returnedOwner;

    @BeforeEach
    void setUp() {
       returnedOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));
        Owner savedById = ownerService.findById(OWNER_ID);
        assertNotNull(savedById);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner savedById = ownerService.findById(OWNER_ID);
        assertNull(savedById);
    }

    @Test
    void save() {
        Owner savedOwner = Owner.builder().id(OWNER_ID).build();
        when(ownerRepository.save(any())).thenReturn(returnedOwner);
        Owner owner = ownerService.save(savedOwner);
        assertNotNull(owner);
    }

    @Test
    void findAll() {
        Set<Owner> returnedOwnerSet = new HashSet<>();
        returnedOwnerSet.add(Owner.builder().id(1L).build());
        returnedOwnerSet.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(returnedOwnerSet);
        Set<Owner> owners = ownerService.findAll();
        assertNotNull(owners);
        assertEquals(2,owners.size());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(OWNER_ID);
        verify(ownerRepository).deleteById(anyLong());
    }

    @Test
    void delete() {
        ownerService.delete(returnedOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);
        Owner byLastName = ownerService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, byLastName.getLastName());

    }
}