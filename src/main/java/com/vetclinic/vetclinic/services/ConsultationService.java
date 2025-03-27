package com.vetclinic.vetclinic.services;

import com.vetclinic.vetclinic.dtos.ConsultationDTO;
import com.vetclinic.vetclinic.models.Consultation;
import com.vetclinic.vetclinic.repositories.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.isNull;

@Service
public class ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;

    public ConsultationDTO saveConsultation(ConsultationDTO consultationDTO) {
        Consultation consultation = convertConsultationDTOtoConsultation(consultationDTO);
        consultation = consultationRepository.save(consultation);
        return convertConsultationtoConsultationDTO(consultation);
    }

    public Consultation findConsultationById(Long id) {
        return consultationRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Consultation not found"));
    }

    public ConsultationDTO findConsultationByDate(Date date) {
        return convertConsultationtoConsultationDTO(consultationRepository.findByDate(date)
                .orElseThrow(() ->
                        new IllegalArgumentException("Consultation not found")));
    }

    public ConsultationDTO updateConsultation(ConsultationDTO consultationDTO) {
        if (isNull(consultationDTO.getId())) {
            throw new IllegalArgumentException("Consultation not found");
        }
        Consultation consultation = consultationRepository.findById(consultationDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Consultation not found"));

        consultation = convertConsultationDTOtoConsultation(consultationDTO);
        consultation = consultationRepository.save(consultation);
        return convertConsultationtoConsultationDTO(consultation);
    }

    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    public Consultation convertConsultationDTOtoConsultation(ConsultationDTO consultationDTO) {
        Consultation consultation = new Consultation();
        consultation.setId(consultationDTO.getId());
        consultation.setDate(consultationDTO.getDate());
        consultation.setConsultory(consultationDTO.getConsultory());
        consultation.setPrice(consultationDTO.getPrice());
        consultation.setRequiredExams(consultationDTO.getRequiredExams());
        return consultation;
    }

    public ConsultationDTO convertConsultationtoConsultationDTO(Consultation consultation) {
        ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setId(consultation.getId());
        consultationDTO.setDate(consultation.getDate());
        consultationDTO.setConsultory(consultation.getConsultory());
        consultationDTO.setPrice(consultation.getPrice());
        consultationDTO.setRequiredExams(consultation.getRequiredExams());
        return consultationDTO;
    }
}
