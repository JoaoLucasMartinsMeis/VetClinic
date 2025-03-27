package com.vetclinic.vetclinic.models;

public enum PetAnimal {
    DOG("Dog"),
    CAT("Cat"),
    FISH("Fish"),
    BIRD("Bird"),
    RABBIT("Rabbit"),
    HAMSTER("Hamster"),
    MOUSE("Mouse"),
    RAT("Rat"),
    GUINEA_PIG("Guinea Pig"),
    REPTILE("Reptile"),
    HEDGEHOG("Hedgehog"),
    OTHER("Other");

    private final String description;

    PetAnimal(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
