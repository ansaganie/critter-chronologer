package com.udacity.jdnd.course3.critter.pet;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertDTOToPet(petDTO);
        Pet savedPet = petService.save(petDTO.getOwnerId(), pet);
        return convertPetToDTO(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
        return convertPetToDTO(petService.getById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getAll().stream().map(this::convertPetToDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
        return petService.getPetsByOwner(ownerId).stream().map(this::convertPetToDTO).collect(Collectors.toList());
    }

    private PetDTO convertPetToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    private Pet convertDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
