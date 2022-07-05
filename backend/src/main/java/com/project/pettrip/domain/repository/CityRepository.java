package com.project.pettrip.domain.repository;

import com.project.pettrip.domain.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
    /**
     * Realiza a busca de cidade pela cidade informada.
     *
     * @param city cidade.
     * @return entidade City encontrada.
     */
    City findByCity(String city);
}
