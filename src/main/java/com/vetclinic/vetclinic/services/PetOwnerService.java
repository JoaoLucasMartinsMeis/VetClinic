package com.vetclinic.vetclinic.services;

import com.vetclinic.vetclinic.dtos.PetOwnerDTO;
import com.vetclinic.vetclinic.models.PetOwner;
import com.vetclinic.vetclinic.repositories.PetOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class PetOwnerService {
    @Autowired
    private PetOwnerRepository petOwnerRepository;

    public PetOwnerDTO savePetOwner(PetOwnerDTO petOwnerDTO) {
        PetOwner petOwner = convertPetOwnerDTOtoPetOwner(petOwnerDTO);
        petOwner = petOwnerRepository.save(petOwner);
        return convertPetOwnertoPetOwnerDTO(petOwner);
    }

    public PetOwner findPetOwnerById(Long id) {
        return petOwnerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pet owner not found"));
    }

    public PetOwnerDTO findPetOwnerByName(String name) {
        return convertPetOwnertoPetOwnerDTO(petOwnerRepository.findByName(name)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pet owner not found")));
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

    public PetOwner convertPetOwnerDTOtoPetOwner(PetOwnerDTO petOwnerDTO) {
        PetOwner petOwner = new PetOwner();
        petOwner.setId(petOwnerDTO.getId());
        petOwner.setCpf(petOwnerDTO.getCpf());
        petOwner.setName(petOwnerDTO.getName());
        petOwner.setEmail(petOwnerDTO.getEmail());
        petOwner.setPhone(petOwnerDTO.getPhone());
        petOwner.setAddress(petOwnerDTO.getAddress());
        return petOwner;
    }

    public PetOwnerDTO convertPetOwnertoPetOwnerDTO(PetOwner petOwner) {
        PetOwnerDTO petOwnerDTO = new PetOwnerDTO();
        petOwnerDTO.setId(petOwner.getId());
        petOwnerDTO.setName(petOwner.getName());
        petOwnerDTO.setCpf(petOwner.getCpf());
        petOwnerDTO.setName(petOwner.getName());
        petOwnerDTO.setEmail(petOwner.getEmail());
        petOwnerDTO.setPhone(petOwner.getPhone());
        petOwnerDTO.setAddress(petOwner.getAddress());
        return petOwnerDTO;
    }
}