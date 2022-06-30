package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.AddressInputDTO;
import com.project.pettrip.api.dto.CityInputDTO;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.domain.model.Address;
import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.model.Filters;
import com.project.pettrip.domain.model.FiltersEnum;
import com.project.pettrip.domain.repository.AddressRepository;
import com.project.pettrip.domain.repository.CityRepository;
import com.project.pettrip.domain.repository.FilterRepository;
import com.project.pettrip.domain.service.impl.AddressServiceImpl;
import com.project.pettrip.domain.service.impl.FilterServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class FiltersServiceImplTest {

    private IFiltrerService filtrerService;

    @MockBean
    private FilterRepository filterRepository;

    @MockBean
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        this.filtrerService = new FilterServiceImpl(filterRepository, modelMapper);
    }


    @Test
    @DisplayName("Deve verificar os gets de Filters")
    void verifyGetsFilters() {
        Filters filters = createFilter();

        Assertions.assertEquals(1L, filters.getId());
        Assertions.assertEquals(FiltersEnum.DOG.getValue(), filters.getType());
        Assertions.assertEquals(FiltersEnum.TINY.getValue(), filters.getWeight());
        Assertions.assertEquals(FiltersEnum.CASTRATED.getValue(), filters.getCastrated());
        Assertions.assertEquals(FiltersEnum.MALE.getValue(), filters.getGender());
    }

    private Filters createFilter() {
        Filters filters = new Filters();
        filters.setId(1L);
        filters.setType(FiltersEnum.DOG.getValue());
        filters.setWeight(FiltersEnum.TINY.getValue());
        filters.setCastrated(FiltersEnum.CASTRATED.getValue());
        filters.setGender(FiltersEnum.MALE.getValue());

        return filters;
    }

}