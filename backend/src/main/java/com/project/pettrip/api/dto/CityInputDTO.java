package com.project.pettrip.api.dto;

public class CityInputDTO {

    private final String city;

    public CityInputDTO(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
