package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.EstablishmentCompleteDTO;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.model.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEstablishmentService {

    Page<EstablishmentSummaryDTO> findByFilters(EstablishmentInputDTO establishment, Pageable pageRequest);

    EstablishmentCompleteDTO create(EstablishmentCompleteDTO establishmentCompleteInputDTO);

    void inactivate(Long establishmentId);
    void activate(Long establishmentId);
    Establishment findEstablishementById(Long estabelecimentoId);
    void delete(Establishment establishment);


}
