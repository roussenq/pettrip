package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.EstablishmentCompleteDTO;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.model.Establishment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Interface para gerenciamento de estabelecimentos.
 */
public interface IEstablishmentService {

    /**
     * Para realização de busca de estabelecimentos pelos filtros informados.
     *
     * @param establishment classe EstablishmentInputDTO.
     * @param pageRequest   Pageable com informações de paginação.
     * @return uma Page de EstablishmentSummaryDTO com os resultados encontrados.
     */
    Page<EstablishmentSummaryDTO> findByFilters(EstablishmentInputDTO establishment, Pageable pageRequest);

    /**
     * Método para criação de estabelecimento.
     *
     * @param establishmentCompleteInputDTO classe EstablishmentCompleteDTO.
     * @return EstablishmentCompleteDTO do estabelecimento salvo.
     */
    EstablishmentCompleteDTO create(EstablishmentCompleteDTO establishmentCompleteInputDTO);

    /**
     * Realiza a inativação do status de um estabelecimento.
     *
     * @param establishmentId do tipo Long.
     */
    void inactivate(Long establishmentId);

    /**
     * Realiza a ativação do status de um estabelecimento.
     *
     * @param establishmentId do tipo Long.
     */
    void activate(Long establishmentId);

    /**
     * Procura um estabelecimento pelo id informado.
     *
     * @param establishmentId do tipo Long.
     * @return entidade Establishment encontrada.
     */
    Establishment findEstablishementById(Long establishmentId);

    /**
     * Método para deletar um estabelecimento.
     *
     * @param establishment do tipo entidade Establishment.
     */
    void delete(Establishment establishment);


}
