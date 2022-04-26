package com.project.pettrip.service;

import com.project.pettrip.dto.EstablishmentInputDTO;
import com.project.pettrip.dto.EstablishmentSummaryDTO;
import com.project.pettrip.model.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface EstablishmentService {

    Page<EstablishmentSummaryDTO> findByFilters(EstablishmentInputDTO establishmentInputDTO, Pageable pageRequest);
}
