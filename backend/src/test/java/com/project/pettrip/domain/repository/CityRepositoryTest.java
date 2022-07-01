package com.project.pettrip.domain.repository;

import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.model.Filters;
import com.project.pettrip.domain.service.impl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
public class CityRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    CityRepository cityRepository;

    @Test
    @DisplayName("Deve retornar uma cidade quando houver esta cidade na base de dados")
    public void entityCityWhenExistCityTest(){
        City city = createCity( "Nova cidade", "SC");
        entityManager.persist(city);
        City existentCity = cityRepository.findByCity("Nova cidade");

        assertThat(existentCity.getId()).isNotNull();
        assertThat(existentCity.getCity()).isEqualTo(city.getCity());
    }

    private City createCity( String city, String state) {
        City cityCreated = new City();
        cityCreated.setCity(city);
        cityCreated.setState(state);
        return cityCreated;
    }
}
