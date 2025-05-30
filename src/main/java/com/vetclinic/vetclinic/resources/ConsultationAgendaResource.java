package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.ConsultationAgendaDTO;
import com.vetclinic.vetclinic.models.ConsultationAgenda;
import com.vetclinic.vetclinic.services.ConsultationAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vetclinic/consultAgenda")
public class ConsultationAgendaResource {

    @Autowired
    private ConsultationAgendaService consultationAgendaService;

    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public ResponseEntity<ConsultationAgendaDTO> findById(@PathVariable Long id) {
        ConsultationAgenda consultationAgenda = consultationAgendaService.findConsultationAgendaById(id);
        return ResponseEntity.ok(consultationAgendaService.convertConsultationAgendatoConsultationAgendaDTO(consultationAgenda));
    }

    @Transactional()
    @GetMapping("/date")
    public ResponseEntity<List<ConsultationAgendaDTO>> findConsultationAgendaByDate(@RequestParam Date date) {
        ConsultationAgendaDTO consultationAgendaDTO = consultationAgendaService.findConsultationAgendaByDate(date);
        return ResponseEntity.ok(List.of(consultationAgendaDTO));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<ConsultationAgendaDTO> createConsultationAgenda(@RequestBody ConsultationAgendaDTO consultationAgendaDTO) {
        return ResponseEntity.ok(consultationAgendaService.saveConsultationAgenda(consultationAgendaDTO));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<ConsultationAgendaDTO> updateConsultationAgenda(@RequestBody ConsultationAgendaDTO consultationAgendaDTO) {
        return ResponseEntity.ok(consultationAgendaService.updateConsultationAgenda(consultationAgendaDTO));
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deleteConsultationAgenda(@RequestBody ConsultationAgendaDTO consultationAgendaDTO)  {
        consultationAgendaService.deleteConsultationAgenda(consultationAgendaDTO.getId());
        return ResponseEntity.noContent().build();
    }

}
