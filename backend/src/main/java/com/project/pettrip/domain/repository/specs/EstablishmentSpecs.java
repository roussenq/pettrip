package com.project.pettrip.domain.repository.specs;

import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.domain.exception.InvalidArgumentException;
import com.project.pettrip.domain.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public abstract class EstablishmentSpecs {

    public static Specification<Establishment> toSpec(EstablishmentInputDTO dto) {
        return (root, query, builder) ->{
            try {
                List<Predicate> predicates = new ArrayList<>();
                Join<Establishment, Filters> filtersJoin = root.join("filters", JoinType.LEFT);
                Join<Establishment, Address> addressJoin = root.join("address", JoinType.LEFT);

                if (StringUtils.hasText(dto.getCityId().toString())) {
                    Path<String> selected = addressJoin.get("city");
                    Predicate predicateType = builder.equal(selected, dto.getCityId());
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(dto.getCityId().toString())) {
                    Path<String> selected = root.get("status");
                    Predicate predicateType = builder.equal(selected, StatusEstablishment.ACTIVE);
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(dto.getType())) {
                    Path<String> selected = filtersJoin.get("type");
                    Predicate predicateType = builder.equal(selected, FiltersEnum.valueOf(dto.getType().toUpperCase()).getValue());
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(dto.getWeight())) {
                    Path<String> selected = filtersJoin.get("weight");
                    Predicate predicateType = builder.equal(selected, FiltersEnum.valueOf(dto.getWeight().toUpperCase()).getValue());
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(dto.getCastrated())) {
                    Path<String> selected = filtersJoin.get("castrated");
                    Predicate predicateType = builder.equal(selected, FiltersEnum.valueOf(dto.getCastrated().toUpperCase()).getValue());
                    predicates.add(predicateType);
                }
                if (StringUtils.hasText(dto.getGender())) {
                    Path<String> selected = filtersJoin.get("gender");
                    Predicate predicateType = builder.equal(selected, FiltersEnum.valueOf(dto.getGender().toUpperCase()).getValue());
                    predicates.add(predicateType);
                }

                query.distinct(true);

                return builder.and(predicates.toArray(new Predicate[0]));
            }catch (InvalidArgumentException ex){
                throw new InvalidArgumentException("Oops... Sinto muito. Não foi possível encontrar resultados com as " +
                        "informações que você deseja, tente novamente.");
            }
        };
    }

}
