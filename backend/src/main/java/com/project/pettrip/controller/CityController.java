package com.project.pettrip.controller;


import com.project.pettrip.dto.CitySummaryDTO;
import com.project.pettrip.model.City;
import com.project.pettrip.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @ApiOperation("Obter lista de cidades existentes no banco")
    @ApiResponse(code = 200, message = "ok")
    @GetMapping
    public List<CitySummaryDTO> findAllCities(){

        List<City> cities = cityService.listCities();
        return cities.stream().map(City::toCitySummaryDTO)
                .sorted((o1, o2) -> o1.getCityAndState().compareToIgnoreCase(o2.getCityAndState()))
                .collect(Collectors.toList());

    }

}
