package com.vetclinic.vetclinic.services;

import com.vetclinic.vetclinic.dtos.DiegnosticDTO;
import com.vetclinic.vetclinic.models.Diegnostic;
import com.vetclinic.vetclinic.repositories.DiegnosticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class DiegnosticService {
    @Autowired
    private DiegnosticRepository diegnosticRepository;

    public DiegnosticDTO saveDiegnostic(DiegnosticDTO diegnosticDTO) {
        Diegnostic diegnostic = convertDiegnosticDTOtoDiegnostic(diegnosticDTO);
        diegnostic = diegnosticRepository.save(diegnostic);
        return convertDiegnostictoDiegnosticDTO(diegnostic);
    }

    public Diegnostic findDiegnosticById(Long id) {
        return diegnosticRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Diegnostic not found"));
    }

    public DiegnosticDTO findDiegnosticByName(String name) {
        return convertDiegnostictoDiegnosticDTO(diegnosticRepository.findByName(name)
                .orElseThrow(() ->
                        new IllegalArgumentException("Diegnostic not found")));
    }

    public DiegnosticDTO updateDiegnostic(DiegnosticDTO diegnosticDTO) {
        if (isNull(diegnosticDTO.getId())) {
            throw new IllegalArgumentException("Diegnostic not found");
        }
        Diegnostic diegnostic = diegnosticRepository.findById(diegnosticDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Diegnostic not found"));

        diegnostic = convertDiegnosticDTOtoDiegnostic(diegnosticDTO);
        diegnostic = diegnosticRepository.save(diegnostic);
        return convertDiegnostictoDiegnosticDTO(diegnostic);
    }

    public void deleteDiegnostic(Long id) {
        diegnosticRepository.deleteById(id);
    }

    public Diegnostic convertDiegnosticDTOtoDiegnostic(DiegnosticDTO diegnosticDTO) {
        Diegnostic diegnostic = new Diegnostic();
        diegnostic.setId(diegnosticDTO.getId());
        diegnostic.setConsultationCause(diegnosticDTO.getConsultationCause());
        diegnostic.setConsideration(diegnosticDTO.getConsideration());
        diegnostic.setTreatment(diegnosticDTO.getTreatment());
        return diegnostic;
    }

    public DiegnosticDTO convertDiegnostictoDiegnosticDTO(Diegnostic diegnostic) {
        DiegnosticDTO diegnosticDTO = new DiegnosticDTO();
        diegnosticDTO.setId(diegnostic.getId());
        diegnosticDTO.setConsultationCause(diegnostic.getConsultationCause());
        diegnosticDTO.setConsideration(diegnostic.getConsideration());
        diegnosticDTO.setTreatment(diegnostic.getTreatment());
        return diegnosticDTO;
    }
}

