package com.vetclinic.vetclinic.repositories;

import com.vetclinic.vetclinic.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
