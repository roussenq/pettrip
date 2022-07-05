package com.project.pettrip.domain.service.impl;

import com.project.pettrip.api.dto.CitySummaryDTO;
import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.repository.CityRepository;
import com.project.pettrip.domain.service.ICityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe da camada Service com a  lógica de implementação referênte à cidades.
 */
@Service
public class CityServiceImpl implements ICityService {

    private final CityRepository cityRepository;

    /**
     * Construtor de instanciação de CityServiceImpl.
     *
     * @param cityRepository referência à Interface CityRepository.
     */
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CitySummaryDTO> listCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(City::toCitySummaryDTO)
                .sorted((o1, o2) -> o1.getCityAndState().compareToIgnoreCase(o2.getCityAndState()))
                .collect(Collectors.toList());
    }

    @Override
    public Long getDefaultCityId() {
        return cityRepository.findByCity("Florianópolis").getId();
    }

    @Override
    public City findByCity(String city) {
        return cityRepository.findByCity(city);
    }

    @Override
    public City create(City city) {
        return cityRepository.save(city);
    }

}
