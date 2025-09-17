package com.vetclinic.vetclinic.Enum;

public enum PetSize {
    VERY_SMALL("Very Small"),
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large"),
    XLARGE("XLarge"),
    XXLARGE("XXLarge");


    private final String description;
    PetSize(String description) {
        this.description = description;
    }
}
