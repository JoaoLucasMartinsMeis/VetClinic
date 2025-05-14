package com.vetclinic.vetclinic.repositories;

import com.vetclinic.vetclinic.models.ConsultationAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ConsultationAgendaRepository extends JpaRepository<ConsultationAgenda, Long> {
    Optional<ConsultationAgenda> findByDate(Date startDate);
}
