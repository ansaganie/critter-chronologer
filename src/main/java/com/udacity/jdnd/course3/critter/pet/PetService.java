package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PetService {

    private final PetRepository repository;
    private final CustomerService customerService;

    public Pet getPet(Long petId) {
        return repository.getOne(petId);
    }

    public Pet save(Long ownerId, Pet pet) {
        Customer customer = customerService.find(ownerId);
        pet.setCustomer(customer);
        Pet savedPet = repository.save(pet);
        customer.addPet(savedPet);
        customerService.save(customer);
        return savedPet;
    }

    public List<Pet> findAll() {
        return repository.findAll();
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        return repository.findAllByCustomer_Id(ownerId);
    }

    public List<Pet> findAll(List<Long> petIds) {
        return repository.findAllById(petIds);
    }
}
