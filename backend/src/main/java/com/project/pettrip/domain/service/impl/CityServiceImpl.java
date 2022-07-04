package com.project.pettrip.domain.service.impl;

import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.repository.CityRepository;
import com.project.pettrip.domain.service.ICityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements ICityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> listCities() {
        return cityRepository.findAll();
    }

    @Override
    public Long getDefaultCityId() {
        return cityRepository.findByCity("Florianópolis").getId();
    }
}
