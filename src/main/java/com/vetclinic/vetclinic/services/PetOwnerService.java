package com.vetclinic.vetclinic.services;

import com.vetclinic.vetclinic.dtos.PetDTO;
import com.vetclinic.vetclinic.dtos.PetOwnerDTO;
import com.vetclinic.vetclinic.models.Pet;
import com.vetclinic.vetclinic.models.PetOwner;
import com.vetclinic.vetclinic.repositories.PetOwnerRepository;
import com.vetclinic.vetclinic.repositories.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class PetOwnerService {
    @Autowired
    private PetOwnerRepository petOwnerRepository;
    @Autowired
    private PetService petService;
    @Autowired
    private PetRepository petRepository;

    public PetOwnerDTO savePetOwner(PetOwnerDTO petOwnerDTO) {
        PetOwner petOwner = convertPetOwnerDTOtoPetOwner(petOwnerDTO);
        petOwner = petOwnerRepository.save(petOwner);
        return convertPetOwnertoPetOwnerDTO(petOwner);
    }

    public List<PetOwnerDTO> findPetOwnersByName(String name) {
        List<PetOwner> owners = petOwnerRepository.findByNameContainingIgnoreCase(name);
        if (owners == null || owners.isEmpty()) {
            return Collections.emptyList();
        }
        return owners.stream()
                .map(this::convertPetOwnertoPetOwnerDTO)
                .collect(Collectors.toList());
    }

    public List<PetOwnerDTO> findPetOwnerAll() {
        List<PetOwner> petOwners = petOwnerRepository.findAll();
        return petOwners.stream().map(this::convertPetOwnertoPetOwnerDTO).collect(Collectors.toList());
    }

    public PetOwnerDTO updatePetOwner(PetOwnerDTO petOwnerDTO) {
        if (isNull(petOwnerDTO.getId())) {
            throw new IllegalArgumentException("Pet owner not found");
        }
        PetOwner petOwner = petOwnerRepository.findById(petOwnerDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("PetOwner not found"));

        petOwner = convertPetOwnerDTOtoPetOwner(petOwnerDTO);
        petOwner = petOwnerRepository.save(petOwner);
        return convertPetOwnertoPetOwnerDTO(petOwner);
    }

    public void deletePetOwner(Long id) {
        petOwnerRepository.deleteById(id);
    }

    @Transactional
    public void addPetOwnerToPet(Long petOwnerId, Long petId) {
        PetOwner petOwner = petOwnerRepository.findById(petOwnerId)
                .orElseThrow(() -> new RuntimeException("PetOwner not found"));
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        petOwner.addPet(pet);
        petOwnerRepository.save(petOwner);
    }

    @Transactional
    public void removeAssociationPO(Long petOwnerId, Long petId) {
        PetOwner petOwner = petOwnerRepository.findById(petOwnerId)
                .orElseThrow(() -> new RuntimeException("PetOwner not found"));
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        petOwner.removePet(pet);
        petRepository.save(pet);
    }

    public PetOwner findPetOwnerById(Long id) {
        return petOwnerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pet owner not found"));
    }

    public List<PetDTO> getPetsByOwnerId(Long ownerId) {
        PetOwner owner = findPetOwnerById(ownerId);
        if (owner.getPets() == null || owner.getPets().isEmpty()) {
            return Collections.emptyList();
        }
        return owner.getPets().stream()
                .map(pet -> {
                    PetDTO dto = new PetDTO();
                    dto.setId(pet.getId());
                    dto.setName(pet.getName());
                    dto.setAnimal(pet.getAnimal());
                    dto.setBreed(pet.getBreed());
                    // Apenas dados básicos para evitar loop
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public PetOwner convertPetOwnerDTOtoPetOwner(PetOwnerDTO petOwnerDTO) {
        PetOwner petOwner = new PetOwner();
        petOwner.setId(petOwnerDTO.getId());
        petOwner.setCpf(petOwnerDTO.getCpf());
        petOwner.setName(petOwnerDTO.getName());
        petOwner.setEmail(petOwnerDTO.getEmail());
        petOwner.setPhone(petOwnerDTO.getPhone());
        petOwner.setAddress(petOwnerDTO.getAddress());
       // petOwner.setPets(petService.convertListPetDTOT0Pet(petOwnerDTO.getPets()));
        return petOwner;
    }

    public PetOwnerDTO convertPetOwnertoPetOwnerDTO(PetOwner petOwner) {
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO();
        petOwnerDTO.setId(petOwner.getId());
        petOwnerDTO.setName(petOwner.getName());
        petOwnerDTO.setCpf(petOwner.getCpf());
        petOwnerDTO.setEmail(petOwner.getEmail());
        petOwnerDTO.setPhone(petOwner.getPhone());
        petOwnerDTO.setAddress(petOwner.getAddress());
        return petOwnerDTO;
    }
}