package com.project.pettrip.domain.service.impl;

import com.project.pettrip.api.dto.EstablishmentCompleteDTO;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.exception.BusinessException;
import com.project.pettrip.domain.exception.InvalidArgumentException;
import com.project.pettrip.domain.model.Establishment;
import com.project.pettrip.domain.model.StatusEstablishment;
import com.project.pettrip.domain.repository.EstablishmentRepository;
import com.project.pettrip.domain.service.IAddressService;
import com.project.pettrip.domain.service.IEstablishmentService;
import com.project.pettrip.domain.service.IFiltrerService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.Optional;

import static com.project.pettrip.domain.repository.specs.EstablishmentSpecs.toSpec;

/**
 * The type Establishment service.
 */
@Service
public class EstablishmentServiceImpl implements IEstablishmentService {

    private final EstablishmentRepository establishmentRepository;

    private final ModelMapper modelMapper;

    private final IAddressService addressService;

    private final IFiltrerService filtrerService;

    public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository, ModelMapper modelMapper, IAddressService addressService, IFiltrerService filtrerService) {
        this.establishmentRepository = establishmentRepository;
        this.modelMapper = modelMapper;
        this.addressService = addressService;
        this.filtrerService = filtrerService;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EstablishmentSummaryDTO> findByFilters(EstablishmentInputDTO establishmentInputDTO, Pageable pageRequest) {

        Page<Establishment> resultPage = establishmentRepository.findAll(toSpec(establishmentInputDTO), pageRequest);
        if (resultPage.isEmpty())
            throw new BusinessException("Oops... Sinto muito. Não foi possível encontrar resultados com as informações que você deseja, tente novamente.");

        return resultPage.map(this::toEstablishmentSummaryDTO);
    }

    @Transactional
    @Override
    public EstablishmentCompleteDTO create(EstablishmentCompleteDTO establishmentCompleteInputDTO) {

        Establishment establishment = toEstablishmentEntity(establishmentCompleteInputDTO);

        validateEstablishment(establishment);

        establishment.setAddress(addressService.create(establishmentCompleteInputDTO.getAddress()));
        establishment.setFilters(filtrerService.create(establishmentCompleteInputDTO.getFilters()));
        establishment.setStatus(StatusEstablishment.ACTIVE);
        establishment.setCreatedAt(OffsetDateTime.now());
        Establishment establishmentSaved = establishmentRepository.save(establishment);

        return toEstablishmentCompleteDTO(establishmentSaved);
    }

    private void validateEstablishment(Establishment establishment) {
        boolean existentCNPJ = establishmentRepository.findByCnpj(establishment.getCnpj())
                .stream().anyMatch(estabelecimentoExistente -> !(estabelecimentoExistente.equals(establishment)));
        if (existentCNPJ){
            throw new BusinessException("Já existe um estabelecimento cadastrado com esse CNPJ.");
        }
    }

    @Override
    public void inactivate(Long establishmentId) {
        Establishment establishment = findEstablishementById(establishmentId);
        establishment.inactivate();
        establishmentRepository.save(establishment);
    }

    @Override
    public void activate(Long establishmentId) {
        Establishment establishment = findEstablishementById(establishmentId);
        establishment.activate();
        establishmentRepository.save(establishment);
    }
    @Override
    public Establishment findEstablishementById(Long establishmentId) {
        return establishmentRepository.findById(establishmentId).orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado!"));
    }

    @Override
    public void delete(Establishment establishment) {
        if (establishment == null || establishment.getId() == null)
            throw new InvalidArgumentException("O Id do estabelecimento não pode ser nulo.");

        establishmentRepository.delete(establishment);
    }

    private Establishment toEstablishmentEntity(EstablishmentCompleteDTO dto){
        return modelMapper.map(dto, Establishment.class);
    }

    private EstablishmentCompleteDTO toEstablishmentCompleteDTO(Establishment establishment){
        return modelMapper.map(establishment, EstablishmentCompleteDTO.class);
    }

    private EstablishmentSummaryDTO toEstablishmentSummaryDTO(Establishment establishment){
        return modelMapper.map(establishment, EstablishmentSummaryDTO.class);
    }

}
