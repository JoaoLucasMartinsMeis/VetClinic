package com.vetclinic.vetclinic.dtos;

public class UserDTO {

    private Long id;
    private String cpf;
    private String name;
    private String officeHours; // mantido (só faz sentido para VET)
    private String email;
    private String password; // recebido na criação/atualização, NÃO deve ser exposto no frontend
    private String role; // "VET" ou "USER"

    public UserDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOfficeHours() { return officeHours; }
    public void setOfficeHours(String officeHours) { this.officeHours = officeHours; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
