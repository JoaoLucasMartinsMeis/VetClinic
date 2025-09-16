package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "petOwner")
public class PetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String name;
    private String email;
    private String phone;
    private String address;

    public PetOwner(Long id, String cpf, String name, String email, String phone, String address) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.getPetOwners().add(this);
    }
    public void removePet(Pet pet) {
        pets.remove(pet);
        pet.getPetOwners().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetOwner)) return false;
        PetOwner that = (PetOwner) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToMany(mappedBy = "petOwners")
    private Set<Pet> pets = new HashSet<>();

    @OneToMany(mappedBy = "petOwners", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consultation> consultationsO = new ArrayList<>();

    // Construtor que inicializa as listas
    public PetOwner() {
        this.pets = new HashSet<>();
        this.consultationsO = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", id=" + id +
                '}';
    }
}
