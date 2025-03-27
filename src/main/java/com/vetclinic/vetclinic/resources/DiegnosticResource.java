package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.DiegnosticDTO;
import com.vetclinic.vetclinic.models.Diegnostic;
import com.vetclinic.vetclinic.services.DiegnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vetclinic/diegnostics")
public class DiegnosticResource {

    @Autowired
    private DiegnosticService diegnosticService;

    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public ResponseEntity<DiegnosticDTO> findById(@PathVariable Long id) {
        Diegnostic diegnostic = diegnosticService.findDiegnosticById(id);
        return ResponseEntity.ok(diegnosticService.convertDiegnostictoDiegnosticDTO(diegnostic));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<DiegnosticDTO> createDiegnostic(@RequestBody DiegnosticDTO diegnosticDTO) {
        return ResponseEntity.ok(diegnosticService.saveDiegnostic(diegnosticDTO));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<DiegnosticDTO> updateDiegnostic(@RequestBody DiegnosticDTO diegnosticDTO) {
        return ResponseEntity.ok(diegnosticService.updateDiegnostic(diegnosticDTO));
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deleteDiegnostic(@RequestBody DiegnosticDTO diegnosticDTO)  {
        diegnosticService.deleteDiegnostic(diegnosticDTO.getId());
        return ResponseEntity.noContent().build();
    }

}
