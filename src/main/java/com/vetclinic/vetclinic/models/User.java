package com.vetclinic.vetclinic.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

import com.vetclinic.vetclinic.Enum.Role;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;
    private String name;
    private String officeHours;

    @Column(unique = true)
    private String email;

    private String password; // armazenado hasheado

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    private List<Consultation> consultationsV;

    public User() {}

    public User(Long id, String cpf, String name, String officeHours) {
        this.cpf = cpf;
        this.name = name;
        this.officeHours = officeHours;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(name, that.name) &&
                Objects.equals(officeHours, that.officeHours) &&
                Objects.equals(consultationsV, that.consultationsV) &&
                Objects.equals(email, that.email) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, name, officeHours, consultationsV, email, role);
    }
}
