package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.FilterDTO;
import com.project.pettrip.domain.model.Filters;

import java.util.List;

/**
 * Interface para gerenciamento de filtros.
 */
public interface IFiltrerService {

    /**
     * Método para criar filtros.
     *
     * @param filters uma lista de FilterDTO.
     * @return uma lista de entidades Filters salvos.
     */
    List<Filters> create(List<FilterDTO> filters);
}
