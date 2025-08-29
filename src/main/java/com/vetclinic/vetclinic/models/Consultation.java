package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private Date endDate;
    private int office;
    private double price;
    private String requiredExams;
    private String consultationCause;
    private String diagnostic;
    private String treatment;

    public Consultation() {}

    public Consultation(Long id, Date startDate, Date endDate, int office, double price, String requiredExams, String consultationCause, String diagnostic, String treatment) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.office = office;
        this.price = price;
        this.requiredExams = requiredExams;
        this.consultationCause = consultationCause;
        this.diagnostic = diagnostic;
        this.treatment = treatment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consultation that = (Consultation) o;
        return office == that.office && Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(requiredExams, that.requiredExams) && Objects.equals(consultationCause, that.consultationCause) && Objects.equals(diagnostic, that.diagnostic) && Objects.equals(treatment, that.treatment) && Objects.equals(pets, that.pets) && Objects.equals(users, that.users) && Objects.equals(petOwners, that.petOwners) && Objects.equals(consultationAgenda, that.consultationAgenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, office, price, requiredExams, consultationCause, diagnostic, treatment, pets, users, petOwners, consultationAgenda);
    }

    @ManyToOne
    @JoinColumn(name = "consultationP")
    private Pet pets;

    @ManyToOne
    @JoinColumn(name = "consultationV")
    private User users;

    @ManyToOne
    @JoinColumn(name = "consultationO")
    private PetOwner petOwners;

    @ManyToOne
    @JoinColumn(name = "consultationA")
    private ConsultationAgenda consultationAgenda;
}
