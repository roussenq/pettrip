package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.FilterDTO;
import com.project.pettrip.domain.model.Filters;

import java.util.List;

public interface IFiltrerService {

    List<Filters> create(List<FilterDTO> filters);
}
