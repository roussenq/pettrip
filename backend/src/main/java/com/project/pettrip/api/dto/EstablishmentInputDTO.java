package com.project.pettrip.api.dto;

public class EstablishmentInputDTO {

    private final Long cityId;
    private final String type;
    private final String weight;
    private final String castrated;
    private final String gender;

    public EstablishmentInputDTO(Long cityId, String type, String weight, String castrated, String gender) {
        this.cityId = cityId;
        this.type = type;
        this.weight = weight;
        this.castrated = castrated;
        this.gender = gender;
    }

    public Long getCityId() {
        return cityId;
    }

    public String getType() {
        return type;
    }

    public String getWeight() {
        return weight;
    }

    public String getCastrated() {
        return castrated;
    }

    public String getGender() {
        return gender;
    }

}
