package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "consultationAgenda")
public class ConsultationAgenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String description;

    public ConsultationAgenda() {}

    public ConsultationAgenda(Long id, Date date, String description) {
        this.date = date;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ConsultationAgenda that = (ConsultationAgenda) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(description, that.description) && Objects.equals(consultationsA, that.consultationsA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, description, consultationsA);
    }

    @OneToMany
    private List<Consultation> consultationsA;
}
