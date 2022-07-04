package com.project.pettrip.domain.service;

import com.project.pettrip.domain.model.City;

import java.util.List;

public interface ICityService {

    List<City> listCities();

    Long getDefaultCityId();
}
