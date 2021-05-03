package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
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

    public List<Pet> getAll() {
        List<Pet> pets = repository.findAll();
        if (pets.size() == 0) throw new IllegalStateException("There is no pets yet");
        else return pets;
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        Customer customer = customerService.getById(ownerId);
        return repository.findAllByCustomer(customer);
    }

    public List<Pet> getAll(List<Long> petsIds) {
        List<Pet> pets = repository.findAllById(petsIds);
        if (pets.size() == 0) throw new IllegalStateException("There is no pets with ids = <" + pets + ">");
        else return pets;
    }
}
