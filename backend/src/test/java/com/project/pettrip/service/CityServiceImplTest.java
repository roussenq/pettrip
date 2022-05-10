package com.project.pettrip.service;

import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.repository.CityRepository;
import com.project.pettrip.domain.service.CityService;
import com.project.pettrip.domain.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CityServiceImplTest {

    private CityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
        this.cityService = new CityServiceImpl(cityRepository);
    }

    @Test
    @DisplayName("Deve listar as cidades existentes no banco")
    void listCitiesThatStartsWith() {
        City city1 = createCity(1L, "cidadeTeste", "SC");
        City city2 = createCity(2L, "cidadeTeste", "PR");

        Mockito.when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        List<City> results = cityService.listCities();

        Assertions.assertNotNull(results);
        Assertions.assertEquals(2, results.size());
        Mockito.verify(cityRepository, Mockito.times(1)).findAll();
    }

    private City createCity(Long id, String city, String state) {
        return new City(id, city, state);
    }


}