package com.vetclinic.vetclinic.services;

import com.vetclinic.vetclinic.dtos.PetDTO;
import com.vetclinic.vetclinic.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
}
