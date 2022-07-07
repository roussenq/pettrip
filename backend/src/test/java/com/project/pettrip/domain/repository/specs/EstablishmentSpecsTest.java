package com.project.pettrip.domain.repository.specs;

import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.domain.model.Establishment;
import com.project.pettrip.domain.model.FiltersEnum;
import com.project.pettrip.domain.repository.EstablishmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
        EstablishmentInputDTO dto = createSearchFilters();

        Page<Establishment> resultPage = establishmentRepository.findAll(EstablishmentSpecs.toSpec(dto), pageRequest);

        assertThat(resultPage.isEmpty()).isFalse();
        assertThat(StringUtils.hasText(dto.getCityId().toString())).isTrue();
        assertThat(StringUtils.hasText(dto.getType())).isTrue();
        assertThat(StringUtils.hasText(dto.getWeight())).isTrue();
        assertThat(StringUtils.hasText(dto.getCastrated())).isTrue();
        assertThat(StringUtils.hasText(dto.getGender())).isTrue();
    }

//    @Test
//    @DisplayName("Deve verificar que não há texto no filtros.")
//    public void findBySpecificationWithoutTextTest(){
//
//        PageRequest pageRequest = PageRequest.of(0, 6);
//        EstablishmentInputDTO dto = createSearchWithoutFilter();
//
//        establishmentRepository.findAll(EstablishmentSpecs.toSpec(Mockito.any()), pageRequest);
//
//        assertThat(StringUtils.hasText(dto.getCityId().toString())).isFalse();
//        assertThat(StringUtils.hasText(dto.getType())).isFalse();
//        assertThat(StringUtils.hasText(dto.getWeight())).isFalse();
//        assertThat(StringUtils.hasText(dto.getCastrated())).isFalse();
//        assertThat(StringUtils.hasText(dto.getGender())).isFalse();
//    }

    private EstablishmentInputDTO createSearchWithoutFilter() {
        return new EstablishmentInputDTO();
    }


    @Test
    @DisplayName("Deve retornar uma exception quando informados filtros inválidos")
    public void throwExceptionWhenFindBySpecificationTest(){

        PageRequest pageRequest = PageRequest.of(0, 6);
        EstablishmentInputDTO dto = createSearchFilterWithError();

        Throwable exception = Assertions.catchThrowable(() -> establishmentRepository.findAll(EstablishmentSpecs.toSpec(dto), pageRequest));

        Assertions.assertThat(exception).isInstanceOf(RuntimeException.class);
    }


    private EstablishmentInputDTO createSearchFilters() {
        EstablishmentInputDTO establishmentInputDTO = new EstablishmentInputDTO();
        establishmentInputDTO.setCityId(1L);
        establishmentInputDTO.setType("dog");
        establishmentInputDTO.setWeight("tiny");
        establishmentInputDTO.setCastrated("castrated");
        establishmentInputDTO.setGender("male");
        return establishmentInputDTO;
    }

    private EstablishmentInputDTO createSearchFilterWithError() {
        EstablishmentInputDTO establishmentInputDTO = new EstablishmentInputDTO();
        establishmentInputDTO.setCityId(1L);
        establishmentInputDTO.setType("dogg");
        establishmentInputDTO.setWeight(FiltersEnum.TINY.getValue());
        establishmentInputDTO.setCastrated(FiltersEnum.CASTRATED.getValue());
        establishmentInputDTO.setGender(FiltersEnum.MALE.getValue());
        return establishmentInputDTO;
    }

}