package com.project.pettrip.api.dto;

/**
 * DTO com as informações necessárias para realizar a busca filtrada de estabelecimentos.
 */
public class EstablishmentInputDTO {

    private Long cityId;
    private String type;
    private String weight;
    private String castrated;
    private String gender;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCastrated() {
        return castrated;
    }

    public void setCastrated(String castrated) {
        this.castrated = castrated;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
