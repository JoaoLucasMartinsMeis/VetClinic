package com.vetclinic.vetclinic.services;


import com.vetclinic.vetclinic.dtos.PetDTO;
import com.vetclinic.vetclinic.models.Pet;
import com.vetclinic.vetclinic.models.PetOwner;
import com.vetclinic.vetclinic.repositories.PetOwnerRepository;
import com.vetclinic.vetclinic.repositories.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    private PetOwnerRepository petOwnerRepository;

    public PetDTO savePet(PetDTO petDTO) {
        Pet pet = convertPetDTOtoPet(petDTO);
        pet = petRepository.save(pet);
        return convertPettoPetDTO(pet);
    }

    public Pet findPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pet not found"));
    }

    public PetDTO findPetByName(String name) {
        return convertPettoPetDTO(petRepository.findByName(name)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pet not found")));
    }

    public PetDTO updatePet(PetDTO petDTO) {
        if (isNull(petDTO.getId())) {
            throw new IllegalArgumentException("Pet not found");
        }
        Pet pet = petRepository.findById(petDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Pet not found"));

        pet = convertPetDTOtoPet(petDTO);
        pet = petRepository.save(pet);
        return convertPettoPetDTO(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public PetService(PetRepository petRepository, PetOwnerRepository petOwnerRepository) {
        this.petRepository = petRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    @Transactional
    public void addPetToPetOwner(Long petId, Long petOwnerId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        PetOwner petOwner = petOwnerRepository.findById(petOwnerId)
                .orElseThrow(() -> new RuntimeException("PetOwner not found"));

        pet.addPetOwner(petOwner);
        // salvando o lado dominante
        petOwnerRepository.save(petOwner);
    }

    @Transactional
    public void removeAssociation(Long petId, Long petOwnerId) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        PetOwner petOwner = petOwnerRepository.findById(petOwnerId)
                .orElseThrow(() -> new RuntimeException("PetOwner not found"));

        pet.removePetOwner(petOwner);
        petOwnerRepository.save(petOwner);
    }

    public Pet convertPetDTOtoPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setAnimal(petDTO.getAnimal());
        pet.setBreed(petDTO.getBreed());
        pet.setSize(petDTO.getSize());
        pet.setAge(petDTO.getAge());
        pet.setWeight(petDTO.getWeight());
        pet.setSex(petDTO.getSex());
        return pet;
    }

    public PetDTO convertPettoPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setAnimal(pet.getAnimal());
        petDTO.setBreed(pet.getBreed());
        petDTO.setSize(pet.getSize());
        petDTO.setAge(pet.getAge());
        petDTO.setWeight(pet.getWeight());
        petDTO.setSex(pet.getSex());
        return petDTO;
    }

    public Set<Pet> convertListPetDTOT0Pet(Set<PetDTO> pets) {
        return pets.stream().map(this::convertPetDTOtoPet).collect(Collectors.toSet());
    }

    public Set<PetDTO> convertListPetT0PetDTO(Set<Pet> pets) {
        return pets.stream().map(this::convertPettoPetDTO).collect(Collectors.toSet());
    }

    public List<PetDTO> findAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(this::convertPettoPetDTO)
                .collect(Collectors.toList());
    }
}
