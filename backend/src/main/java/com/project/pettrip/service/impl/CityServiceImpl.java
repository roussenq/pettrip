package com.project.pettrip.service.impl;

import com.project.pettrip.model.City;
import com.project.pettrip.repository.CityRepository;
import com.project.pettrip.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> listCities() {
        return cityRepository.findAll();
    }
}
