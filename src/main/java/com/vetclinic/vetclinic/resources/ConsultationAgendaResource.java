package com.vetclinic.vetclinic.resources;

import com.vetclinic.vetclinic.dtos.ConsultationAgendaDTO;
import com.vetclinic.vetclinic.models.ConsultationAgenda;
import com.vetclinic.vetclinic.services.ConsultationAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vetclinic/consultAgenda")
public class ConsultationAgendaResource {

    @Autowired
    private ConsultationAgendaService consultationAgendaService;

    @Transactional(readOnly = true)
    @GetMapping("{id}")
    public ResponseEntity<ConsultationAgendaDTO> findById(@PathVariable Long id) {
        ConsultationAgenda consultationAgenda = consultationAgendaService.findConsultationAgendaById(id);
        return ResponseEntity
                .ok(consultationAgendaService.convertConsultationAgendatoConsultationAgendaDTO(consultationAgenda));
    }

    @Transactional
    @GetMapping("/year/{year}")
    public ResponseEntity<List<ConsultationAgendaDTO>> getAgendaByYear(@PathVariable int year) {
        List<ConsultationAgendaDTO> agendaList = consultationAgendaService.findByYear(year);
        return ResponseEntity.ok(agendaList);
    }

    @Transactional
    @GetMapping("/month/{year}/{month}")
    public ResponseEntity<List<ConsultationAgendaDTO>> getAgendaByMonth(@PathVariable int year,
            @PathVariable int month) {
        List<ConsultationAgendaDTO> agendaList = consultationAgendaService.findByMonth(year, month);
        return ResponseEntity.ok(agendaList);
    }

    @Transactional
    @GetMapping("/day")
    public ResponseEntity<List<ConsultationAgendaDTO>> getAgendaByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        ConsultationAgendaDTO consultationAgendaDTO = consultationAgendaService.findConsultationAgendaByDate(date);
        return ResponseEntity.ok(List.of(consultationAgendaDTO));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<ConsultationAgendaDTO> createConsultationAgenda(
            @RequestBody ConsultationAgendaDTO consultationAgendaDTO) {
        return ResponseEntity.ok(consultationAgendaService.saveConsultationAgenda(consultationAgendaDTO));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<ConsultationAgendaDTO> updateConsultationAgenda(
            @RequestBody ConsultationAgendaDTO consultationAgendaDTO) {
        return ResponseEntity.ok(consultationAgendaService.updateConsultationAgenda(consultationAgendaDTO));
    }

    @Transactional
    @DeleteMapping()
    public ResponseEntity<Void> deleteConsultationAgenda(@RequestBody ConsultationAgendaDTO consultationAgendaDTO) {
        consultationAgendaService.deleteConsultationAgenda(consultationAgendaDTO.getId());
        return ResponseEntity.noContent().build();
    }

}
