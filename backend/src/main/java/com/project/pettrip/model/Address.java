package com.project.pettrip.model;

import com.project.pettrip.dto.AddressDTO;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "tb_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String district;

    @Valid
    @NotNull
    @ManyToOne
    private City city;

    @NotBlank
    @Column(name = "zip_code")
    private String zipCode;


    public Address(){

    }

    public Address(String street, String number, String complement, String district, City city, String zipCode) {
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

    public Address(City city){
        this.city = city;
    }


    public City getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city);
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

    public String getZipCode() {
        return zipCode;
    }

    public AddressDTO toAddressDTO() {
        return new AddressDTO(id, street,number,complement,district,
                city.toCityDTO(),
                zipCode);
    }
}
