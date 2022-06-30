package com.project.pettrip.domain.model;

import com.project.pettrip.api.dto.CityDTO;
import com.project.pettrip.api.dto.CitySummaryDTO;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city1 = (City) o;
        return Objects.equals(getCity(), city1.getCity()) && Objects.equals(getState(), city1.getState());
    }



    public CitySummaryDTO toCitySummaryDTO(){
        return new CitySummaryDTO(id, city, state);
    }


}
