package com.vetclinic.vetclinic.services;

import com.vetclinic.vetclinic.dtos.ConsultationAgendaDTO;
import com.vetclinic.vetclinic.models.ConsultationAgenda;
import com.vetclinic.vetclinic.repositories.ConsultationAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ConsultationAgendaService {
    @Autowired
    private ConsultationAgendaRepository consultationAgendaRepository;

    public ConsultationAgendaDTO saveConsultationAgenda(ConsultationAgendaDTO consultationAgendaDTO) {
        ConsultationAgenda consultationAgenda = convertConsultationAgendaDTOtoConsultationAgenda(consultationAgendaDTO);
        consultationAgenda = consultationAgendaRepository.save(consultationAgenda);
        return convertConsultationAgendatoConsultationAgendaDTO(consultationAgenda);
    }

    public ConsultationAgenda findConsultationAgendaById(Long id) {
        return consultationAgendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ConsultationAgenda not found"));
    }

    public ConsultationAgendaDTO findConsultationAgendaByDate(Date date) {
        return convertConsultationAgendatoConsultationAgendaDTO(consultationAgendaRepository.findByDate(date)
                .orElseThrow(() -> new IllegalArgumentException("ConsultationAgenda not found")));
    }

    public List<ConsultationAgendaDTO> findByYear(int year) {
        return consultationAgendaRepository.findByYear(year)
                .stream()
                .map(this::convertConsultationAgendatoConsultationAgendaDTO)
                .toList();
    }

    public List<ConsultationAgendaDTO> findByMonth(int year, int month) {
        return consultationAgendaRepository.findByMonth(year, month)
                .stream()
                .map(this::convertConsultationAgendatoConsultationAgendaDTO)
                .toList();
    }

    public ConsultationAgendaDTO updateConsultationAgenda(ConsultationAgendaDTO consultationAgendaDTO) {
        if (isNull(consultationAgendaDTO.getId())) {
            throw new IllegalArgumentException("ConsultationAgenda not found");
        }
        ConsultationAgenda consultationAgenda = consultationAgendaRepository.findById(consultationAgendaDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("ConsultationAgenda not found"));

        consultationAgenda = convertConsultationAgendaDTOtoConsultationAgenda(consultationAgendaDTO);
        consultationAgenda = consultationAgendaRepository.save(consultationAgenda);
        return convertConsultationAgendatoConsultationAgendaDTO(consultationAgenda);
    }

    public void deleteConsultationAgenda(Long id) {
        consultationAgendaRepository.deleteById(id);
    }

    public ConsultationAgenda convertConsultationAgendaDTOtoConsultationAgenda(
            ConsultationAgendaDTO consultationAgendaDTO) {
        ConsultationAgenda consultationAgenda = new ConsultationAgenda();
        consultationAgenda.setId(consultationAgendaDTO.getId());
        consultationAgenda.setDate(consultationAgendaDTO.getDate());
        consultationAgenda.setDescription(consultationAgendaDTO.getDescription());
        return consultationAgenda;
    }

    public ConsultationAgendaDTO convertConsultationAgendatoConsultationAgendaDTO(
            ConsultationAgenda consultationAgenda) {
        ConsultationAgendaDTO consultationAgendaDTO = new ConsultationAgendaDTO();
        consultationAgendaDTO.setId(consultationAgenda.getId());
        consultationAgendaDTO.setDate(consultationAgenda.getDate());
        consultationAgendaDTO.setDescription(consultationAgenda.getDescription());
        return consultationAgendaDTO;
    }
}
