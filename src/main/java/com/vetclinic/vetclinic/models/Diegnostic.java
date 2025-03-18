package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "diegnostic")
public class Diegnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String consultationCause;
    String Consideration;
    String Treatment;

    public Diegnostic() {}

    public Diegnostic(Long id, String consultationCause, String Consideration, String Treatment) {
        this.consultationCause = consultationCause;
        this.Consideration = Consideration;
        this.Treatment = Treatment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Diegnostic that = (Diegnostic) o;
        return Objects.equals(id, that.id) && Objects.equals(consultationCause, that.consultationCause) && Objects.equals(Consideration, that.Consideration) && Objects.equals(Treatment, that.Treatment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, consultationCause, Consideration, Treatment);
    }
}
