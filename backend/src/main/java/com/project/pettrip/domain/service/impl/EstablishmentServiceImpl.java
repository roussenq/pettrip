package com.project.pettrip.domain.service.impl;

import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.exception.BusinessException;
import com.project.pettrip.domain.model.Address;
import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.model.Establishment;
import com.project.pettrip.domain.model.Filters;
import com.project.pettrip.domain.repository.EstablishmentRepository;
import com.project.pettrip.domain.repository.specs.EstablishmentSpecs;
import com.project.pettrip.domain.service.EstablishmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.pettrip.domain.repository.specs.EstablishmentSpecs.toSpec;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EstablishmentSummaryDTO> findByFilters(EstablishmentInputDTO establishmentInputDTO, Pageable pageRequest) {


        Page<Establishment> resultPage = establishmentRepository.findAll(toSpec(establishmentInputDTO), pageRequest);
        if (resultPage.isEmpty()){
            throw new BusinessException("Oops... Sinto muito. Não foi possível encontrar resultados com as informações " +
                    "que você deseja, tente novamente.");
        }
        return resultPage.map(Establishment::toEstablishmentSummayDTO);
    }

}
