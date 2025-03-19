package com.vetclinic.vetclinic.repositories;

import com.vetclinic.vetclinic.models.Veterinary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeterinaryRepository extends JpaRepository<Veterinary, Long> {
    Optional<Veterinary> findByName(String name);
}
