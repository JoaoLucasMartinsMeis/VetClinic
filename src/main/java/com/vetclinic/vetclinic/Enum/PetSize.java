package com.vetclinic.vetclinic.Enum;

public enum PetSize {
    VERYSMALL("Very Small"),
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large"),
    XLARGE("X Large"),
    XXLARGE("XX Large");


    private final String description;
    PetSize(String description) {
        this.description = description;
    }
}
