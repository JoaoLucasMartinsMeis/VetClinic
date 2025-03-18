package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String breed;
    private String size;
    private String weight;
    private PetSex sex;

    public Pet() {}

    public Pet(Long id, String name, String breed, String size, String weight, PetSex sex) {
        this.name = name;
        this.breed = breed;
        this.size = size;
        this.weight = weight;
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(name, pet.name) && Objects.equals(breed, pet.breed) && Objects.equals(size, pet.size) && Objects.equals(weight, pet.weight) && sex == pet.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, size, weight, sex);
    }
}
