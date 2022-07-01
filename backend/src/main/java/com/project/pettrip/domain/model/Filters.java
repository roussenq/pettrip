package com.project.pettrip.domain.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Classe para objetos do tipo Filters.
 */
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
}
