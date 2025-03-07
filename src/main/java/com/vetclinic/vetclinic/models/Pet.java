package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_pet")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public PetSex getSex() {
        return sex;
    }

    public void setSex(PetSex sex) {
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
