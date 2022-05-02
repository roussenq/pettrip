package com.project.pettrip.api.dto;

import com.project.pettrip.domain.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

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

    public Specification<Establishment> toSpec() {
        return (root, query, builder) ->{
            try {
                List<Predicate> predicates = new ArrayList<>();
                Join<Establishment, Filters> filtersJoin = root.join("filters", JoinType.LEFT);
                Join<Establishment, Address> addressJoin = root.join("address", JoinType.LEFT);

                if (StringUtils.hasText(cityId.toString())) {
                    Path<String> selected = addressJoin.get("city");
                    Predicate predicateType = builder.equal(selected, this.cityId);
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(cityId.toString())) {
                    Path<String> selected = root.get("status");
                    Predicate predicateType = builder.equal(selected, StatusEstablishment.ACTIVE);
                    predicates.add(predicateType);
                }

                if (StringUtils.hasText(type)) {
                    Path<String> selected = filtersJoin.get("type");
                    Predicate predicateType = builder.equal(selected, FiltersEnum.valueOf(this.type.toUpperCase()).getValue());
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(weight)) {
                    Path<String> selected = filtersJoin.get("weight");
                    Predicate predicateType = builder.equal(selected, FiltersEnum.valueOf(this.weight.toUpperCase()).getValue());
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(castrated)) {
                    Path<String> selected = filtersJoin.get("castrated");
                    Predicate predicateType = builder.equal(selected, FiltersEnum.valueOf(this.castrated.toUpperCase()).getValue());
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(gender)) {
                    Path<String> selected = filtersJoin.get("gender");
                    Predicate predicateType = builder.equal(selected, FiltersEnum.valueOf(this.gender.toUpperCase()).getValue());
                    predicates.add(predicateType);
                }

                query.groupBy(root.get("id"));
                return builder.and(predicates.toArray(new Predicate[0]));
            }catch (IllegalArgumentException ex){
                throw new IllegalArgumentException("Oops... Sinto muito. Não foi possível encontrar resultados com as " +
                        "informações que você deseja, tente novamente.");
            }
        };
    }
}
