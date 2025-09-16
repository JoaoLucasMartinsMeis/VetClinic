package com.vetclinic.vetclinic.models;

import com.vetclinic.vetclinic.Enum.PetAnimal;
import com.vetclinic.vetclinic.Enum.PetSex;
import com.vetclinic.vetclinic.Enum.PetSize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

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

    @ManyToMany
    @JoinTable(
            name = "pet_petOwner",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "petOwner_id")
    )
    private Set<PetOwner> petOwners = new HashSet<>();

    @OneToMany(mappedBy = "pets", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consultation> consultationP = new ArrayList<>();

    public Pet() {
        this.petOwners = new HashSet<>();
        this.consultationP = new ArrayList<>();
    }

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
