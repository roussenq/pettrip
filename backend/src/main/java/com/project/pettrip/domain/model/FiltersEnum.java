package com.project.pettrip.domain.model;

/**
 * Classe para enums de Filters.
 */
public enum FiltersEnum {

    DOG("dog"), CAT("cat"),
    TINY("5kg"), SMALL("10kg"), MEDIUM("15kg"), BIG("20kg"),
    MALE("male"), FEMALE("female"),
    CASTRATED("castrated"), UNCASTRATED("uncastrated");

    private final String value;

    FiltersEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
