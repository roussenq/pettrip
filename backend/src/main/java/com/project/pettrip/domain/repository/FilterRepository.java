package com.project.pettrip.domain.repository;

import com.project.pettrip.domain.model.Filters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<Filters, Long>, JpaSpecificationExecutor<Filters> {

    Filters findByTypeAndWeightAndCastratedAndGender(String type, String weight, String castrated, String gender);
}
