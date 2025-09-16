package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.PetDTO;
import com.vetclinic.vetclinic.dtos.PetOwnerDTO;
import com.vetclinic.vetclinic.models.Pet;
import com.vetclinic.vetclinic.models.PetOwner;
import com.vetclinic.vetclinic.repositories.PetOwnerRepository;
import com.vetclinic.vetclinic.services.PetOwnerService;
import com.vetclinic.vetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vetclinic/petOwners")
public class PetOwnerResource {

    @Autowired
    private PetOwnerService petOwnerService;
    private PetService petService;

    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public ResponseEntity<PetOwnerDTO> findById(@PathVariable Long id) {
        PetOwner petOwner = petOwnerService.findPetOwnerById(id);
        return ResponseEntity.ok(petOwnerService.convertPetOwnertoPetOwnerDTO(petOwner));
    }

    @Transactional()
    @GetMapping("/name")
    public ResponseEntity<List<PetOwnerDTO>> findPetOwnerByName(@RequestParam String name) {
        List<PetOwnerDTO> ownerDTOs = petOwnerService.findPetOwnersByName(name);
        return ResponseEntity.ok(ownerDTOs);
    }

    @Transactional()
    @GetMapping("/all")
    public ResponseEntity<List<PetOwnerDTO>> findPetOwnerAll() {
        List<PetOwnerDTO> petOwnerDTOs = petOwnerService.findPetOwnerAll();
        return ResponseEntity.ok(petOwnerDTOs);
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<PetOwnerDTO> createPetOwner(@RequestBody PetOwnerDTO petOwnerDTO) {
        return ResponseEntity.ok(petOwnerService.savePetOwner(petOwnerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetOwnerDTO> updatePetOwner(@PathVariable Long id, @RequestBody PetOwnerDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(petOwnerService.updatePetOwner(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetOwner(@PathVariable Long id) {
        petOwnerService.deletePetOwner(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/petOwner/{petOwnerId}/pet/{petId}")
    public String addPetOwnerToPet(@PathVariable Long petOwnerId, @PathVariable Long petId) {
        petOwnerService.addPetOwnerToPet(petOwnerId, petId);
        return "Association between PetOwner and Pet added successfully.";
    }

    @DeleteMapping("/petOwner/{petOwnerId}/pet/{petId}")
    public String removeAssociationPO(@PathVariable Long petOwnerId, @PathVariable Long petId) {
        petOwnerService.removeAssociationPO(petOwnerId, petId);
        return "Association between PetOwner and Pet removed successfully.";
    }

    @GetMapping("/{ownerId}/pets")
    public ResponseEntity<List<PetDTO>> getPetsByOwner(@PathVariable Long ownerId) {
        List<PetDTO> pets = petOwnerService.getPetsByOwnerId(ownerId);
        return ResponseEntity.ok(pets);
    }
}