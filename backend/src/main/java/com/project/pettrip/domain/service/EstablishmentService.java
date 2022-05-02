package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.model.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstablishmentService {

    Page<EstablishmentSummaryDTO> findByFilters(EstablishmentInputDTO establishment, Pageable pageRequest);
}
