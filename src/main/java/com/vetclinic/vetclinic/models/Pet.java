package com.vetclinic.vetclinic.models;

import com.vetclinic.vetclinic.Enum.PetAnimal;
import com.vetclinic.vetclinic.Enum.PetSex;
import com.vetclinic.vetclinic.Enum.PetSize;
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
    private PetSize size;
    private int age;
    private String weight;
    private PetSex sex;

    public Pet() {}

    public Pet(Long id, String name, PetAnimal animal, String breed, PetSize size, int age,String weight, PetSex sex) {
        this.id = id;
        this.name = name;
        this.animal = animal;
        this.breed = breed;
        this.size = size;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
    }

    public void addPetOwner(PetOwner petOwner) {
        petOwners.add(petOwner);
        petOwner.getPets().add(this);
    }
    public void removePetOwner(PetOwner petOwner) {
        petOwners.remove(petOwner);
        petOwner.getPets().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet that = (Pet) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @OneToMany
    private List<Consultation> consultationP;

    @ManyToMany
    private Set<PetOwner> petOwners = new HashSet<>();

    @Override
    public String toString() {
        return "Pet{" +
                "sex=" + sex +
                ", weight='" + weight + '\'' +
                ", age=" + age +
                ", size='" + size + '\'' +
                ", breed='" + breed + '\'' +
                ", animal=" + animal +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
