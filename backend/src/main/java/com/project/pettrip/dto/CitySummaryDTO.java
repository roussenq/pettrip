package com.project.pettrip.dto;

public class CitySummaryDTO {

    private final Long id;
    private final String cityAndState;

    public CitySummaryDTO(Long id, String city, String state) {
        this.id = id;
        this.cityAndState = city + ", " + state + ".";
    }

    public String getCityAndState() {
        return cityAndState;
    }

    public Long getId() {
        return id;
    }
}
