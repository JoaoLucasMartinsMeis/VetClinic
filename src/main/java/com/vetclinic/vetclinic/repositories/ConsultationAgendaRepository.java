package com.vetclinic.vetclinic.repositories;

import com.vetclinic.vetclinic.models.ConsultationAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationAgendaRepository extends JpaRepository<ConsultationAgenda, Long> {

    Optional<ConsultationAgenda> findByDate(Date startDate);

    @Query("SELECT a FROM ConsultationAgenda a WHERE YEAR(a.date) = :year")
    List<ConsultationAgenda> findByYear(int year);

    @Query("SELECT a FROM ConsultationAgenda a WHERE YEAR(a.date) = :year AND MONTH(a.date) = :month")
    List<ConsultationAgenda> findByMonth(int year, int month);
}
