package com.vetclinic.vetclinic.models;

public enum PetSex {
    FEMALE("Female"),
    MALE("Male");

    private final String description;

    PetSex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
