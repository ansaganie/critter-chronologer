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

    public Pet getById(Long petId) {
        return repository.findById(petId).orElseThrow(() -> {throw  new IllegalStateException(
                "There is no such pet with id: <" + petId + ">"
            );
        });
    }

    public Pet save(Long ownerId, Pet pet) {
        Customer customer = customerService.getById(ownerId);
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

    public List<Pet> getAll(List<Long> petsIds) {
        return repository.findAllById(petsIds);
    }
}
