package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant date;
    private int consultory;
    private double price;
    private String requiredExams;

    public Consultation() {}

    public Consultation(Long id, Instant date, int consultory, double price, String requiredExams) {
        this.id = id;
        this.date = date;
        this.consultory = consultory;
        this.price = price;
        this.requiredExams = requiredExams;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consultation that = (Consultation) o;
        return consultory == that.consultory && Double.compare(price, that.price) == 0 && Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(requiredExams, that.requiredExams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, consultory, price, requiredExams);
    }
}
