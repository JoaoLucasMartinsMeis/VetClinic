package com.vetclinic.vetclinic.repositories;

import com.vetclinic.vetclinic.models.Diegnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiegnosticRepository extends JpaRepository<Diegnostic, Long> {
    Optional<Diegnostic> findByName(String name);
}
