package com.vetclinic.vetclinic.Enum;

public enum PetSize {
    VERY_SMALL("Very Small"),
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large"),
    X_LARGE("X Large"),
    XX_LARGE("XX Large");


    private final String description;
    PetSize(String description) {
        this.description = description;
    }
}
