package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "veterinary")
public class Veterinary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String name;
    private String officeHours;

    public Veterinary() {}

    public Veterinary(Long id, String cpf, String name, String officeHours) {
        this.cpf = cpf;
        this.name = name;
        this.officeHours = officeHours;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Veterinary that = (Veterinary) o;
        return Objects.equals(id, that.id) && Objects.equals(cpf, that.cpf) && Objects.equals(name, that.name) && Objects.equals(officeHours, that.officeHours) && Objects.equals(consultations, that.consultations) && Objects.equals(consultationAgenda, that.consultationAgenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, name, officeHours, consultations, consultationAgenda);
    }

    @OneToMany(mappedBy = "veterinary")
    private List<Consultation> consultations;

    @ManyToOne
    @JoinColumn(name = "consultationAgenda")
    private ConsultationAgenda consultationAgenda;
}
