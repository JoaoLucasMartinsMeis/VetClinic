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

    private Date date;
    private int consultory;
    private double price;
    private String requiredExams;
    private String consultationCause;
    private String diegnostic;
    private String treatment;

    public Consultation() {}

    public Consultation(Long id, Date date, int consultory, double price, String requiredExams, String consultationCause, String diegnostic, String treatment) {
        this.id = id;
        this.date = date;
        this.consultory = consultory;
        this.price = price;
        this.requiredExams = requiredExams;
        this.consultationCause = consultationCause;
        this.diegnostic = diegnostic;
        this.treatment = treatment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consultation that = (Consultation) o;
        return consultory == that.consultory && Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(requiredExams, that.requiredExams) && Objects.equals(consultationCause, that.consultationCause) && Objects.equals(diegnostic, that.diegnostic) && Objects.equals(treatment, that.treatment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, consultory, price, requiredExams, consultationCause, diegnostic, treatment);
    }
}
