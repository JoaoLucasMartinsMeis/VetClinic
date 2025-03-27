package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.ConsultationDTO;
import com.vetclinic.vetclinic.models.Consultation;
import com.vetclinic.vetclinic.services.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vetclinic/consults")
public class ConsultationResource {

    @Autowired
    private ConsultationService consultationService;

    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public ResponseEntity<ConsultationDTO> findById(@PathVariable Long id) {
        Consultation consultation = consultationService.findConsultationById(id);
        return ResponseEntity.ok(consultationService.convertConsultationtoConsultationDTO(consultation));
    }

    @Transactional()
    @GetMapping("/date")
    public ResponseEntity<List<ConsultationDTO>> findConsultationByDate(@RequestParam Date date) {
        ConsultationDTO consultationDTO = consultationService.findConsultationByDate(date);
        return ResponseEntity.ok(List.of(consultationDTO));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<ConsultationDTO> createConsultation(@RequestBody ConsultationDTO consultationDTO) {
        return ResponseEntity.ok(consultationService.saveConsultation(consultationDTO));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<ConsultationDTO> updateConsultation(@RequestBody ConsultationDTO consultationDTO) {
        return ResponseEntity.ok(consultationService.updateConsultation(consultationDTO));
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deleteConsultation(@RequestBody ConsultationDTO consultationDTO) {
        consultationService.deleteConsultation(consultationDTO.getId());
        return ResponseEntity.noContent().build();
    }

}