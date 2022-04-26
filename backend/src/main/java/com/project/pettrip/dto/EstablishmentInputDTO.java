package com.project.pettrip.dto;

import com.project.pettrip.model.Address;
import com.project.pettrip.model.Establishment;
import com.project.pettrip.model.Filters;
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

            List<Predicate> predicados = new ArrayList<>();
            Join<Establishment, Filters> juntos = root.join("filters", JoinType.LEFT);
            Join<Establishment, Address> juntosEndereco = root.join("address", JoinType.LEFT);

            if (StringUtils.hasText(cityId.toString())) {
                Path<String> testeType = juntosEndereco.get("city");
                Predicate predicadoType = builder.equal(testeType, this.cityId);
                predicados.add(predicadoType);
            }

            if (StringUtils.hasText(type)) {
                Path<String> testeType = juntos.get("type");
                Predicate predicadoType = builder.equal(testeType, this.type);
                predicados.add(predicadoType);
            }
            if (StringUtils.hasText(weight)) {
                Path<String> testeType = juntos.get("weight");
                Predicate predicadoType = builder.equal(testeType, this.weight);
                predicados.add(predicadoType);
            }
            if (StringUtils.hasText(castrated)) {
                Path<String> testeType = juntos.get("castrated");
                Predicate predicadoType = builder.equal(testeType, this.castrated);
                predicados.add(predicadoType);
            }
            if (StringUtils.hasText(gender)) {
                Path<String> testeType = juntos.get("gender");
                Predicate predicadoType = builder.equal(testeType, this.gender);
                predicados.add(predicadoType);
            }
            query.groupBy(root.get("id"));
            return builder.and(predicados.toArray(new Predicate[0]));
        };
    }
}
