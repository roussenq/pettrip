package com.project.pettrip.dto;

public class CityDTO {

    private final Long id;
    private final String city;
    private final String state;

    public CityDTO(Long id, String city, String state){
        this.id = id;
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

}
