package com.project.pettrip.model;

import javax.persistence.*;

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

    public Filters(){

    }

    public Filters(String type, String weight, String castrated, String gender) {
        this.type = type;
        this.weight = weight;
        this.castrated = castrated;
        this.gender = gender;
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
