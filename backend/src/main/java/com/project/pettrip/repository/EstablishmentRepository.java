package com.project.pettrip.repository;

import com.project.pettrip.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long>, JpaSpecificationExecutor<Establishment> {

}
