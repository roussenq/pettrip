package com.project.pettrip.domain.repository;

import com.project.pettrip.domain.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long>, JpaSpecificationExecutor<Establishment> {

    /**
     * Realiza a busca de estabelecimento pelo CNPJ informado.
     *
     * @param cnpj cnpj.
     * @return um Optional de Establishment.
     */
    Optional<Establishment> findByCnpj(String cnpj);

}
