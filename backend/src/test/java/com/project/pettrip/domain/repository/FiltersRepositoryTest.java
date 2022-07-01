package com.project.pettrip.domain.repository;

import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.model.Filters;
import com.project.pettrip.domain.model.FiltersEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class FiltersRepositoryTest {

    @Autowired
    FilterRepository filterRepository;

    @Test
    @DisplayName("Deve retornar uma entidade Filters quando existir filtro identico na base de dados")
    public void entityFilterWhenExistFilterTest(){
        Filters filters = createFilter();

        Filters existentFilters = filterRepository.findByTypeAndWeightAndCastratedAndGender(
                FiltersEnum.DOG.getValue(),
                FiltersEnum.TINY.getValue(),
                FiltersEnum.UNCASTRATED.getValue(),
                FiltersEnum.MALE.getValue()
        );

        assertThat(existentFilters.getId()).isNotNull();
        assertThat(existentFilters.getType()).isEqualTo(filters.getType());
        assertThat(existentFilters.getWeight()).isEqualTo(filters.getWeight());
        assertThat(existentFilters.getCastrated()).isEqualTo(filters.getCastrated());
        assertThat(existentFilters.getGender()).isEqualTo(filters.getGender());
    }

    private Filters createFilter() {
        Filters filters = new Filters();
        filters.setType(FiltersEnum.DOG.getValue());
        filters.setWeight(FiltersEnum.TINY.getValue());
        filters.setCastrated(FiltersEnum.UNCASTRATED.getValue());
        filters.setGender(FiltersEnum.MALE.getValue());

        return filters;
    }
}
