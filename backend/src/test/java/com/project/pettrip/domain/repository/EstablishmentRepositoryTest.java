package com.project.pettrip.domain.repository;

import com.project.pettrip.domain.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class EstablishmentRepositoryTest {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Test
    @DisplayName("Deve retornar um estabelecimento com o CNPJ já existente na base de dados")
    public void entityEstablishmentWhenExistWithCNPJTest(){
        String cnpj = "11.222.333/0004-02";

        Optional<Establishment> existentEstablishment = establishmentRepository.findByCnpj(cnpj);

        assertThat(existentEstablishment.isPresent());
        assertThat(existentEstablishment.get().getCnpj()).isEqualTo(cnpj);
    }

}
