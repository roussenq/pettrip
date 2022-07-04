package com.project.pettrip.api.dto;

public class CityInputDTO {

    private String city;
    private String state;

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
}
