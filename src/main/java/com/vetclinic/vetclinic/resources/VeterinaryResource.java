package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.VeterinaryDTO;
import com.vetclinic.vetclinic.models.Veterinary;
import com.vetclinic.vetclinic.services.VeterinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vetclinic/vets")
public class VeterinaryResource {

    @Autowired
    private VeterinaryService veterinaryService;

    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public ResponseEntity<VeterinaryDTO> findById(@PathVariable Long id) {
        Veterinary veterinary = veterinaryService.findVeterinaryById(id);
        return ResponseEntity.ok(veterinaryService.convertVeterinarytoVeterinaryDTO(veterinary));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<VeterinaryDTO> createVeterinary(@RequestBody VeterinaryDTO veterinaryDTO) {
        return ResponseEntity.ok(veterinaryService.saveVeterinary(veterinaryDTO));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<VeterinaryDTO> updateVeterinary(@RequestBody VeterinaryDTO veterinaryDTO) {
        return ResponseEntity.ok(veterinaryService.updateVeterinary(veterinaryDTO));
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deleteVeterinary(@RequestBody VeterinaryDTO veterinaryDTO)  {
        veterinaryService.deleteVeterinary(veterinaryDTO.getId());
        return ResponseEntity.noContent().build();
    }

}

