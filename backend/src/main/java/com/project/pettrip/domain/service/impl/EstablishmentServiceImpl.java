package com.project.pettrip.domain.service.impl;

import com.project.pettrip.api.dto.EstablishmentCompleteDTO;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.exception.BusinessException;
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

import static com.project.pettrip.domain.repository.specs.EstablishmentSpecs.toSpec;

/**
 * Classe da camada Service com a  lógica de implementação referênte à estabelecimentos.
 */
@Service
public class EstablishmentServiceImpl implements IEstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final ModelMapper modelMapper;
    private final IAddressService addressService;
    private final IFiltrerService filtrerService;

    /**
     * Construtor de instanciação de EstablishmentServiceImpl.
     *
     * @param establishmentRepository referência à Interface EstablishmentRepository.
     * @param modelMapper referência à ModelMapper.
     * @param addressService referência à interface IAddressService.
     * @param filtrerService referência à interface IFiltrerService.
     */
    public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository, ModelMapper modelMapper, IAddressService addressService, IFiltrerService filtrerService) {
        this.establishmentRepository = establishmentRepository;
        this.modelMapper = modelMapper;
        this.addressService = addressService;
        this.filtrerService = filtrerService;
    }

    /**
     * Para realização de busca de estabelecimentos pelos filtros informados.
     *
     * @param establishmentInputDTO classe EstablishmentInputDTO.
     * @param pageRequest   Pageable com informações de paginação.
     * @return uma Page de EstablishmentSummaryDTO com os resultados encontrados.
     * @override interface IEstablishmentService.
     * @throws BusinessException
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EstablishmentSummaryDTO> findByFilters(EstablishmentInputDTO establishmentInputDTO, Pageable pageRequest) {

        Page<Establishment> resultPage = establishmentRepository.findAll(toSpec(establishmentInputDTO), pageRequest);
        if (resultPage.isEmpty())
            throw new BusinessException("Oops... Sinto muito. Não foi possível encontrar hotéis com as buscas que você deseja, tente novamente.");

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

    /**
     * Realiza a validação de estabelecimento para verificar se já existe no banco de dados
     * estabelecimento com o mesmo CNPJ.
     * Caso exista: lança exceção.
     *
     * @param establishment do tipo Establishment.
     * @override interface IEstablishmentService.
     * @throws BusinessException
     */
    private void validateEstablishment(Establishment establishment) {
        boolean existentCNPJ = establishmentRepository.findByCnpj(establishment.getCnpj().replaceAll("[./-]", "").trim())
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

    /**
     * Procura um estabelecimento pelo id informado.
     *
     * @param establishmentId do tipo Long.
     * @return entidade Establishment encontrada.
     * @override interface IEstablishmentService.
     * @throws EntityNotFoundException
     */
    @Override
    public Establishment findEstablishementById(Long establishmentId) {
        return establishmentRepository.findById(establishmentId).orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado!"));
    }

    /**
     * Método para deletar um estabelecimento.
     *
     * @param establishment do tipo entidade Establishment.
     * @override interface IEstablishmentService.
     * @throws IllegalArgumentException
     */
    @Override
    public void delete(Establishment establishment) {
        if (establishment == null || establishment.getId() == null)
            throw new IllegalArgumentException("O Id do estabelecimento não pode ser nulo.");

        establishmentRepository.delete(establishment);
    }

    /**
     * Método para conversão de EstablishmentCompleteDTO para entidade Establishment
     *
     * @param dto EstablishmentCompleteDTO.
     * @return entidade Establishment.
     */
    private Establishment toEstablishmentEntity(EstablishmentCompleteDTO dto){
        return modelMapper.map(dto, Establishment.class);
    }

    /**
     * Método para conversão de Establishment para classe EstablishmentCompleteDTO
     *
     * @param establishment Establishment.
     * @return classe EstablishmentCompleteDTO.
     */
    private EstablishmentCompleteDTO toEstablishmentCompleteDTO(Establishment establishment){
        return modelMapper.map(establishment, EstablishmentCompleteDTO.class);
    }

    /**
     * Método para conversão de Establishment para classe EstablishmentSummaryDTO
     *
     * @param establishment Establishment.
     * @return classe EstablishmentSummaryDTO.
     */
    private EstablishmentSummaryDTO toEstablishmentSummaryDTO(Establishment establishment){
        return modelMapper.map(establishment, EstablishmentSummaryDTO.class);
    }

}
