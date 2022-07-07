package com.project.pettrip.domain.repository;

import com.project.pettrip.domain.model.Filters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<Filters, Long> {

    /**
     * Realiza a busca no banco de dados para verificar se existe filtro igual aos parâmetros informados.
     *
     * @param type      tipo.
     * @param weight    porte.
     * @param castrated se castrado.
     * @param gender    gênero.
     * @return entidade Filters.
     */
    Filters findByTypeAndWeightAndCastratedAndGender(String type, String weight, String castrated, String gender);
}
