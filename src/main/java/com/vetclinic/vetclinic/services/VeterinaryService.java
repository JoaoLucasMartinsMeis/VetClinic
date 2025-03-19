package com.vetclinic.vetclinic.services;

import com.vetclinic.vetclinic.dtos.VeterinaryDTO;
import com.vetclinic.vetclinic.models.Veterinary;
import com.vetclinic.vetclinic.repositories.VeterinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class VeterinaryService {
    @Autowired
    private VeterinaryRepository veterinaryRepository;

    public VeterinaryDTO saveVeterinary(VeterinaryDTO veterinaryDTO) {
        Veterinary veterinary = convertVeterinaryDTOtoVeterinary(veterinaryDTO);
        veterinary = veterinaryRepository.save(veterinary);
        return convertVeterinarytoVeterinaryDTO(veterinary);
    }

    public Veterinary findVeterinaryById(Long id) {
        return veterinaryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Veterinary not found"));
    }

    public VeterinaryDTO findVeterinaryByName(String name) {
        return convertVeterinarytoVeterinaryDTO(veterinaryRepository.findByName(name)
                .orElseThrow(() ->
                        new IllegalArgumentException("Veterinary not found")));
    }

    public VeterinaryDTO updateVeterinary(VeterinaryDTO veterinaryDTO) {
        if (isNull(veterinaryDTO.getId())) {
            throw new IllegalArgumentException("Veterinary not found");
        }
        Veterinary veterinary = veterinaryRepository.findById(veterinaryDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Veterinary not found"));

        veterinary = convertVeterinaryDTOtoVeterinary(veterinaryDTO);
        veterinary = veterinaryRepository.save(veterinary);
        return convertVeterinarytoVeterinaryDTO(veterinary);
    }

    public void deleteVeterinary(Long id) {
        veterinaryRepository.deleteById(id);
    }

    public Veterinary convertVeterinaryDTOtoVeterinary(VeterinaryDTO veterinaryDTO) {
        Veterinary veterinary = new Veterinary();
        veterinary.setId(veterinaryDTO.getId());
        veterinary.setCpf(veterinaryDTO.getCpf());
        veterinary.setName(veterinaryDTO.getName());
        veterinary.setOfficeHours(veterinaryDTO.getOfficeHours());
        return veterinary;
    }

    public VeterinaryDTO convertVeterinarytoVeterinaryDTO(Veterinary veterinary) {
        VeterinaryDTO veterinaryDTO = new VeterinaryDTO();
        veterinaryDTO.setId(veterinary.getId());
        veterinaryDTO.setCpf(veterinary.getCpf());
        veterinaryDTO.setName(veterinary.getName());
        veterinaryDTO.setOfficeHours(veterinary.getOfficeHours());
        return veterinaryDTO;
    }
}