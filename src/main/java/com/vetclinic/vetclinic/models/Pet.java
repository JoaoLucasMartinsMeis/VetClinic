package com.vetclinic.vetclinic.models;

import com.vetclinic.vetclinic.Enum.PetAnimal;
import com.vetclinic.vetclinic.Enum.PetSex;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private PetAnimal animal;
    private String breed;
    private String size;
    private String weight;
    private PetSex sex;

    public Pet() {}

    public Pet(Long id, String name, String breed, String size, String weight, PetSex sex, PetAnimal animal) {
        this.id = id;
        this.name = name;
        this.animal = animal;
        this.breed = breed;
        this.size = size;
        this.weight = weight;
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(name, pet.name) && animal == pet.animal && Objects.equals(breed, pet.breed) && Objects.equals(size, pet.size) && Objects.equals(weight, pet.weight) && sex == pet.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, animal, breed, size, weight, sex);
    }

    @OneToMany(mappedBy = "pet")
    private List<Consultation> consultations;

    @ManyToMany(mappedBy = "pet")
    private Set<PetOwner> petOwners = new HashSet<>();

    public void addPetOwner(PetOwner petOwner) {
        petOwners.add(petOwner);
        petOwner.getPets().add(this);
    }
    public void removePetOwner(PetOwner petOwner) {
        petOwners.remove(petOwner);
        petOwner.getPets().remove(this);
    }

}
