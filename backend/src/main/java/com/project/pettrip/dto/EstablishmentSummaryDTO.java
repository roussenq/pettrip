package com.project.pettrip.dto;

public class EstablishmentSummaryDTO {

    private final Long id;
    private final String cnpj;
    private final String name;
    private final String description;
    private final String email;
    private final String numberPhone;
    private final String image;
    private final AddressDTO address;

    public EstablishmentSummaryDTO(Long id, String cnpj, String name, String description, String email, String numberPhone, String image, AddressDTO address) {
        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.description = description;
        this.email = email;
        this.numberPhone = numberPhone;
        this.image = image;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
