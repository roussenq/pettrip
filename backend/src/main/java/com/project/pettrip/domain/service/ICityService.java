package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.CitySummaryDTO;
import com.project.pettrip.domain.model.City;

import java.util.List;

/**
 * Interface para gerenciamento de cidades.
 */
public interface ICityService {

    /**
     * Método para listar as cidades existentes.
     *
     * @return uma lista de CitySummaryDTO.
     */
    List<CitySummaryDTO> listCities();

    /**
     * Método para obter a cidade default.
     *
     * @return o id da cidade default.
     */
    Long getDefaultCityId();

    /**
     * Método para buscar por nome de cidade.
     *
     * @param city (um nome de cidade).
     * @return uma entidadeCity.
     */
    City findByCity(String city);

    /**
     * Método para criar uma nova cidade.
     *
     * @param city do tipo City.
     * @return entidade City salva.
     */
    City create(City city);
}
