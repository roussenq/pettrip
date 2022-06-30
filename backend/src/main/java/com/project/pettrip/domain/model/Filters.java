package com.project.pettrip.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_filters")
public class Filters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String weight;
    private String castrated;
    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toLowerCase();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight.toLowerCase();
    }

    public String getCastrated() {
        return castrated;
    }

    public void setCastrated(String castrated) {
        this.castrated = castrated.toLowerCase();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filters filters = (Filters) o;
        return Objects.equals(id, filters.id) && Objects.equals(type, filters.type) && Objects.equals(weight, filters.weight) && Objects.equals(castrated, filters.castrated) && Objects.equals(gender, filters.gender);
    }

}
