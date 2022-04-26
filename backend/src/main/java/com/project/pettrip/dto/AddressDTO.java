package com.project.pettrip.dto;

public class AddressDTO {

    private final Long id;
    private final String street;
    private final String number;
    private final String complement;
    private final String district;
    private final CityDTO city;
    private final String zipCode;


    public AddressDTO(Long id, String street, String number, String complement, String district, CityDTO city, String zipCode) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.zipCode = zipCode;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public CityDTO getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }
}
