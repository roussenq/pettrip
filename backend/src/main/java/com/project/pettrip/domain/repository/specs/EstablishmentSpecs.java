package com.project.pettrip.domain.repository.specs;

import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.domain.model.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrada com implementação da criação de consulta dinâmica.
 */
public abstract class EstablishmentSpecs {

    /**
     * Método que cria a query para consulta dinâmica, verificando quais campos
     * de EstablishmentInputDTO estão preenchidos para montar a query com base nesses argumentos.
     *
     * @param dto classe EstablishmentInputDTO.
     * @return um Specification de Establishment.
     * @throws IllegalArgumentException
     */
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
            }catch (IllegalArgumentException ex){
                throw new IllegalArgumentException("Oops... Sinto muito. Não foi possível encontrar hotéis com as buscas" +
                                                    " que você deseja, tente novamente.");
            }
        };
    }
}
