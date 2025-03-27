package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.PetOwnerDTO;
import com.vetclinic.vetclinic.models.PetOwner;
import com.vetclinic.vetclinic.services.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vetclinic/petOwners")
public class PetOwnerResource {

    @Autowired
    private PetOwnerService petOwnerService;

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

    @Transactional
    @PostMapping()
    public ResponseEntity<PetOwnerDTO> createPetOwner(@RequestBody PetOwnerDTO petOwnerDTO) {
        return ResponseEntity.ok(petOwnerService.savePetOwner(petOwnerDTO));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<PetOwnerDTO> updatePetOwner(@RequestBody PetOwnerDTO petOwnerDTO) {
        return ResponseEntity.ok(petOwnerService.updatePetOwner(petOwnerDTO));
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deletePetOwner(@RequestBody PetOwnerDTO petOwnerDTO)  {
        petOwnerService.deletePetOwner(petOwnerDTO.getId());
        return ResponseEntity.noContent().build();
    }

}