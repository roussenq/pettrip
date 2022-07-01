package com.project.pettrip.domain.repository.specs;

import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.domain.model.*;
import com.project.pettrip.domain.repository.EstablishmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class EstablishmentSpecsTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Test
    @DisplayName("Deve retornar um Page de Establishment com os filtros informados")
    public void findBySpecificationTest(){

        PageRequest pageRequest = PageRequest.of(0, 6);
        EstablishmentInputDTO dto = createSearchFilter();

        Page<Establishment> resultPage = establishmentRepository.findAll(EstablishmentSpecs.toSpec(dto), pageRequest);

        assertThat(resultPage.isEmpty()).isFalse();
        assertThat(StringUtils.hasText(dto.getCityId().toString())).isTrue();
        assertThat(StringUtils.hasText(dto.getType())).isTrue();
        assertThat(StringUtils.hasText(dto.getWeight())).isTrue();
        assertThat(StringUtils.hasText(dto.getCastrated())).isTrue();
        assertThat(StringUtils.hasText(dto.getGender())).isTrue();
    }
    private EstablishmentInputDTO createSearchFilter() {
        return new EstablishmentInputDTO(1L,
                FiltersEnum.DOG.name(),
                FiltersEnum.TINY.name(),
                FiltersEnum.CASTRATED.name(),
                FiltersEnum.MALE.name());
    }

}