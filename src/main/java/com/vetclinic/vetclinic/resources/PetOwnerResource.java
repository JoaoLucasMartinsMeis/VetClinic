package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.PetOwnerDTO;
import com.vetclinic.vetclinic.models.PetOwner;
import com.vetclinic.vetclinic.services.PetOwnerService;
import com.vetclinic.vetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        PetOwnerDTO petOwnerDTO = petOwnerService.findPetOwnerByName(name);
        return ResponseEntity.ok(List.of(petOwnerDTO));
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
}