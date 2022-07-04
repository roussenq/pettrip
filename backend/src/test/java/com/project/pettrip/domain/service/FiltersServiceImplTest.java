package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.AddressInputDTO;
import com.project.pettrip.api.dto.CityInputDTO;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.FilterDTO;
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
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

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
    @DisplayName("Deve criar uma lista de filtros com filtro existente.")
    void createFiltersWithExistentFilterTest() {
        Filters filters = createFilter();

        BDDMockito.given(filterRepository.findByTypeAndWeightAndCastratedAndGender(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).willReturn(filters);
        BDDMockito.given(modelMapper.map(Mockito.any(FilterDTO.class), Mockito.any())).willReturn(filters);

        List<Filters> returnedFiltersList = filtrerService.create(createFiltersDTO());

        Assertions.assertEquals(1, returnedFiltersList.size());
    }

    @Test
    @DisplayName("Deve criar uma lista de filtros sem filtro existente.")
    void createFiltersWithoutExistentFilterTest() {
        Filters filters = createFilter();

        BDDMockito.given(filterRepository.findByTypeAndWeightAndCastratedAndGender(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).willReturn(null);
        BDDMockito.given(modelMapper.map(Mockito.any(FilterDTO.class), Mockito.any())).willReturn(filters);
        BDDMockito.given(filterRepository.save(Mockito.any(Filters.class))).willReturn(filters);

        List<Filters> returnedFiltersList = filtrerService.create(createFiltersDTO());

        Assertions.assertEquals(1, returnedFiltersList.size());

    }

    @Test
    @DisplayName("Deve verificar os gets de Filters")
    void verifyGetsFiltersTest() {
        Filters filters = createFilter();
        filters.setId(1L);

        Assertions.assertEquals(1L, filters.getId());
        Assertions.assertEquals(FiltersEnum.DOG.getValue(), filters.getType());
        Assertions.assertEquals(FiltersEnum.TINY.getValue(), filters.getWeight());
        Assertions.assertEquals(FiltersEnum.CASTRATED.getValue(), filters.getCastrated());
        Assertions.assertEquals(FiltersEnum.MALE.getValue(), filters.getGender());
    }

    private Filters createFilter() {
        Filters filters = new Filters();
        filters.setType(FiltersEnum.DOG.getValue());
        filters.setWeight(FiltersEnum.TINY.getValue());
        filters.setCastrated(FiltersEnum.CASTRATED.getValue());
        filters.setGender(FiltersEnum.MALE.getValue());

        return filters;
    }

    private List<Filters> createFilters() {
        Filters filter = new Filters();
        filter.setType(FiltersEnum.DOG.getValue());
        filter.setWeight(FiltersEnum.TINY.getValue());
        filter.setCastrated(FiltersEnum.CASTRATED.getValue());
        filter.setGender(FiltersEnum.MALE.getValue());
        List<Filters> filters = new ArrayList<>();
        filters.add(filter);
        return filters;
    }

    private List<FilterDTO> createFiltersDTO() {
        FilterDTO filter = new FilterDTO();
        filter.setType(FiltersEnum.DOG.getValue());
        filter.setWeight(FiltersEnum.TINY.getValue());
        filter.setCastrated(FiltersEnum.CASTRATED.getValue());
        filter.setGender(FiltersEnum.MALE.getValue());
        List<FilterDTO> filters = new ArrayList<>();
        filters.add(filter);
        return filters;
    }

}