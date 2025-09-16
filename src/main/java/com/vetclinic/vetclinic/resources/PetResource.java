package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.PetDTO;
import com.vetclinic.vetclinic.dtos.PetOwnerDTO;
import com.vetclinic.vetclinic.models.Pet;
import com.vetclinic.vetclinic.models.PetOwner;
import com.vetclinic.vetclinic.repositories.PetRepository;
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
@RequestMapping("/vetclinic/pets")
public class PetResource {

    @Autowired
    private PetService petService;

    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
        Pet pet = petService.findPetById(id);
        return ResponseEntity.ok(petService.convertPettoPetDTO(pet));
    }

    @Transactional()
    @GetMapping("/name")
    public ResponseEntity<List<PetDTO>> findPetByName(@RequestParam String name) {
        List<PetDTO> petDTOs = petService.findPetsByName(name);
        return ResponseEntity.ok(petDTOs);
    }

    @GetMapping()
    public ResponseEntity<List<PetDTO>> findAllPets() {
        List<PetDTO> petDTOs = petService.findAllPets();
        return ResponseEntity.ok(petDTOs);
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<PetDTO> createPet(@RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.savePet(petDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable Long id, @RequestBody PetDTO petDTO) {
        petDTO.setId(id); // ou use no service
        return ResponseEntity.ok(petService.updatePet(petDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/pet/{petId}/petOwner/{petOwnerId}")
    public String addPetToPetOwner(@PathVariable Long petId, @PathVariable Long petOwnerId) {
        petService.addPetToPetOwner(petId, petOwnerId);
        return "Association between Pet and PetOwner added successfully.";
    }

    @DeleteMapping("/pet/{petId}/petOwner/{petOwnerId}")
    public String removeAssociation(@PathVariable Long petId, @PathVariable Long petOwnerId) {
        petService.removeAssociation(petId, petOwnerId);
        return "Association between Pet and PetOwner removed successfully.";
    }

    @GetMapping("/{petId}/owners")
    public ResponseEntity<List<PetOwnerDTO>> getOwnersByPet(@PathVariable Long petId) {
        List<PetOwnerDTO> owners = petService.getOwnersByPetId(petId);
        return ResponseEntity.ok(owners);
    }
}
