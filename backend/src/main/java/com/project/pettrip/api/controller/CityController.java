package com.project.pettrip.api.controller;


import com.project.pettrip.api.dto.CitySummaryDTO;
import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.service.ICityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe controller responsável pelo gerenciamento dos endpoints de cidades.
 */
@RestController()
@RequestMapping("/cities")
public class CityController {

    private final ICityService cityService;

    /**
     * Construtor de instanciação de CityController.
     *
     * @param cityService referência à interface ICityService.
     */
    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Endpoint para obter uma lista de todas cidades cadastradas no banco de dados.
     *
     * @return uma lista de CitySummaryDTO.
     *
     */
    @ApiOperation("Obtains list of cities existing in database.")
    @ApiResponse(code = 200, message = "ok")
    @GetMapping
    public List<CitySummaryDTO> findAllCities(){

        List<City> cities = cityService.listCities();
        return cities.stream().map(City::toCitySummaryDTO)
                .sorted((o1, o2) -> o1.getCityAndState().compareToIgnoreCase(o2.getCityAndState()))
                .collect(Collectors.toList());
    }
}
