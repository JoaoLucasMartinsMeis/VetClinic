package com.vetclinic.vetclinic.repositories;

import com.vetclinic.vetclinic.models.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {
    Optional<PetOwner> findByName(String name);
    List<PetOwner> findByNameContainingIgnoreCase(String name);
}