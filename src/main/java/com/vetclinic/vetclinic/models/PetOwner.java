package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    public PetOwner() {}

    public PetOwner(Long id, String cpf, String name, String email, String phone, String address) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PetOwner petOwner = (PetOwner) o;
        return Objects.equals(id, petOwner.id) && Objects.equals(cpf, petOwner.cpf) && Objects.equals(name, petOwner.name) && Objects.equals(email, petOwner.email) && Objects.equals(phone, petOwner.phone) && Objects.equals(address, petOwner.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, name, email, phone, address);
    }
}
