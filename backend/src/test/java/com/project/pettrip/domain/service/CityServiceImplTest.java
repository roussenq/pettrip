package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.CitySummaryDTO;
import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.repository.CityRepository;
import com.project.pettrip.domain.service.impl.CityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class CityServiceImplTest {

    private ICityService cityService;

    @MockBean
    private CityRepository cityRepository;

    @BeforeEach
    void setUp() {
        this.cityService = new CityServiceImpl(cityRepository);
    }

    @Test
    @DisplayName("Deve listar as cidades existentes no banco")
    void listCitiesTest() {
        City city1 = createCity(1L, "cidadeTeste", "SC");
        City city2 = createCity(2L, "cidadeTeste", "PR");

        Mockito.when(cityRepository.findAll()).thenReturn(List.of(city1, city2));

        List<CitySummaryDTO> results = cityService.listCities();

        Assertions.assertNotNull(results);
        Assertions.assertEquals(2, results.size());
        Mockito.verify(cityRepository, Mockito.times(1)).findAll();
        Assertions.assertNotEquals(city1, city2);
    }

    @Test
    @DisplayName("Deve retornar cidade de Florianópolis")
    void returnCityWithFlorianopolisIdTest() {
        City city = createCity(1L, "Florianópolis", "SC");

        Mockito.when(cityRepository.findByCity(Mockito.any(String.class))).thenReturn(city);

        Long result = cityService.getDefaultCityId();

        Assertions.assertEquals(1L, result);
        Assertions.assertEquals("Florianópolis", city.getCity());
        Assertions.assertEquals("SC", city.getState());

    }

    private City createCity(Long id, String city, String state) {
        City cityCreated = new City();
        cityCreated.setId(id);
        cityCreated.setCity(city);
        cityCreated.setState(state);
        return cityCreated;
    }

}
