package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.PetDTO;
import com.vetclinic.vetclinic.models.Pet;
import com.vetclinic.vetclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        PetDTO petDTO = petService.findPetByName(name);
        return ResponseEntity.ok(List.of(petDTO));
    }
    
    @Transactional
    @PostMapping()
    public ResponseEntity<PetDTO> createPet(@RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.savePet(petDTO));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<PetDTO> updatePet(@RequestBody PetDTO petDTO) {
        return ResponseEntity.ok(petService.updatePet(petDTO));
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deletePet(@RequestBody PetDTO petDTO)  {
        petService.deletePet(petDTO.getId());
        return ResponseEntity.noContent().build();
    }

}
