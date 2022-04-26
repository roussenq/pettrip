package com.project.pettrip.model;

import com.project.pettrip.dto.CitySummaryDTO;
import com.project.pettrip.dto.CityDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "tb_city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    public City() {
    }

    public City(Long id) {
        this.id = id;
    }

    public City(Long id, String city, String state) {
        this.id = id;
        this.city = city;
        this.state = state;
    }

    public City(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city1 = (City) o;
        return Objects.equals(getCity(), city1.getCity()) && Objects.equals(getState(), city1.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getState());
    }

    public CitySummaryDTO toCitySummaryDTO(){
        return new CitySummaryDTO(id, city, state);
    }

    public CityDTO toCityDTO() {
        return new CityDTO(id, city, state);
    }
}
