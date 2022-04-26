package com.project.pettrip.service.impl;

import com.project.pettrip.dto.EstablishmentInputDTO;
import com.project.pettrip.dto.EstablishmentSummaryDTO;
import com.project.pettrip.exception.BusinessException;
import com.project.pettrip.model.Establishment;
import com.project.pettrip.model.StatusEstablishment;
import com.project.pettrip.repository.EstablishmentRepository;
import com.project.pettrip.service.EstablishmentService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EstablishmentSummaryDTO> findByFilters(EstablishmentInputDTO establishmentInputDTO, Pageable pageRequest) {
        Page<Establishment> resultPage = establishmentRepository.findAll(establishmentInputDTO.toSpec(), pageRequest);
        if (resultPage.isEmpty()){
            throw new BusinessException("Oops... Sinto muito. Não foi possível encontrar resultados com as informações " +
                    "que você deseja, tente novamente.");
        }
        return resultPage.map(Establishment::toEstablishmentSummayDTO);
    }
}
