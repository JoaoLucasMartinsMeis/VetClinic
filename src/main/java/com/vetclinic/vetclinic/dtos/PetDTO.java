package com.vetclinic.vetclinic.dtos;

import com.vetclinic.vetclinic.Enum.PetAnimal;
import com.vetclinic.vetclinic.Enum.PetSex;
import com.vetclinic.vetclinic.Enum.PetSize;

public class PetDTO {

    private Long id;
    private String name;
    private PetAnimal animal;
    private String breed;
    private PetSize size;
    private int age;
    private String weight;
    private PetSex sex;

    public PetDTO() {}

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

    public PetAnimal getAnimal() {
        return animal;
    }
    public void setAnimal(PetAnimal animal) {
        this.animal = animal;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public PetSize getSize() {
        return size;
    }

    public void setSize(PetSize size) {
        this.size = size;
    }

    public int getAge() {
        return age;}
    public void setAge(int age) {
        this.age = age;}

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
}
