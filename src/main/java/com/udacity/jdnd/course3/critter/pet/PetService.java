package com.udacity.jdnd.course3.critter.pet;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService {

    private final PetRepository repository;

    public Pet getPet(Long petId) {
        return repository.getOne(petId);
    }
}
