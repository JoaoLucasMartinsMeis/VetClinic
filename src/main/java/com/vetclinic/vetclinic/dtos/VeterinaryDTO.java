package com.vetclinic.vetclinic.dtos;

public class VeterinaryDTO {

    private Long id;
    private String cpf;
    private String name;
    private String officeHours;

    public VeterinaryDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeHours() {
        return officeHours;
    }
    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }
}
