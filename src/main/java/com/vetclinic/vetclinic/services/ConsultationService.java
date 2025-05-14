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

    public ConsultationDTO findConsultationByStartDate(Date startDate) {
        return convertConsultationtoConsultationDTO(consultationRepository.findByStartDate(startDate)
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
        consultation.setStartDate(consultationDTO.getStartDate());
        consultation.setEndDate(consultationDTO.getEndDate());
        consultation.setOffice(consultationDTO.getOffice());
        consultation.setPrice(consultationDTO.getPrice());
        consultation.setRequiredExams(consultationDTO.getRequiredExams());
        consultation.setConsultationCause(consultationDTO.getConsultationCause());
        consultation.setDiagnostic(consultationDTO.getDiagnostic());
        consultation.setTreatment(consultationDTO.getTreatment());
        return consultation;
    }

    public ConsultationDTO convertConsultationtoConsultationDTO(Consultation consultation) {
        ConsultationDTO consultationDTO = new ConsultationDTO();
        consultationDTO.setId(consultation.getId());
        consultationDTO.setStartDate(consultation.getStartDate());
        consultationDTO.setEndDate(consultation.getEndDate());
        consultationDTO.setOffice(consultation.getOffice());
        consultationDTO.setPrice(consultation.getPrice());
        consultationDTO.setRequiredExams(consultation.getRequiredExams());
        consultationDTO.setConsultationCause(consultation.getConsultationCause());
        consultationDTO.setDiagnostic(consultation.getDiagnostic());
        consultationDTO.setTreatment(consultation.getTreatment());
        return consultationDTO;
    }
}
