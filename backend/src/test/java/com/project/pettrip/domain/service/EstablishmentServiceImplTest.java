package com.project.pettrip.domain.service;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.exception.BusinessException;
import com.project.pettrip.domain.model.*;
import com.project.pettrip.domain.repository.EstablishmentRepository;
import com.project.pettrip.domain.service.CityService;
import com.project.pettrip.domain.service.EstablishmentService;
import com.project.pettrip.domain.service.impl.EstablishmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class EstablishmentServiceImplTest {

    private EstablishmentService establishmentService;

    @MockBean
    private EstablishmentRepository establishmentRepository;

    @BeforeEach
    void setUp() {
        this.establishmentService = new EstablishmentServiceImpl(establishmentRepository);
    }

    @Test
    @DisplayName("Deve apresentar página com lista de estabelecimentos que possuem os filtros informados")
    void listByFilters() {

        Page<Establishment> page = createPage();

        Mockito.when(establishmentRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(page);

        Page<EstablishmentSummaryDTO> establishmentPage = establishmentService.findByFilters(createSearchFilter(), page.getPageable());

        Assertions.assertNotNull(establishmentPage);
        Assertions.assertEquals(6, establishmentPage.getSize());
        Assertions.assertEquals(1, establishmentPage.getTotalPages());
        Assertions.assertEquals(2, establishmentPage.getTotalElements());
        Assertions.assertEquals(0, establishmentPage.getPageable().getPageNumber());
        //Assertions.assertInstanceOf(Page<EstablishmentSummaryDTO.class>, establishmentPage.getContent());
    }

    @Test
    @DisplayName("Deve apresentar página com lista de estabelecimentos em Florianópolis quando não houver id da cidade nos filtros")
    void listByFiltersWithoutCity() {

        Page<Establishment> page = createPage();

        Mockito.when(establishmentRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(page);

        Page<EstablishmentSummaryDTO> establishmentPage = establishmentService.findByFilters(createSearchFilterWhitoutCityId(), page.getPageable());

        Assertions.assertNotNull(establishmentPage);
        Assertions.assertEquals(6, establishmentPage.getSize());
        Assertions.assertEquals(1, establishmentPage.getTotalPages());
        Assertions.assertEquals(2, establishmentPage.getTotalElements());
        Assertions.assertEquals(0, establishmentPage.getPageable().getPageNumber());
        Assertions.assertEquals("Florianópolis", establishmentPage.getContent().get(0).getAddress().getCity().getCity());
    }

    @Test
    @DisplayName("Deve apresentar erro quando não encontrar estabelecimentos com os filtros informados")
    void listByInexistentsFilters() {
        Mockito.when(establishmentRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(Page.empty());

        Assertions.assertThrows(BusinessException.class, () -> establishmentService.findByFilters(createSearchFilter(), PageRequest.of(0, 6)));
    }

    private PageImpl<Establishment> createPage() {
        Establishment establishment = createEstablishment();
        List<Establishment> listEstablishment = Arrays.asList(establishment, establishment);
        Pageable pageRequest = PageRequest.of(0, 6);
        return new PageImpl<>(listEstablishment, pageRequest, 1);
    }


    private EstablishmentInputDTO createSearchFilter() {
        return new EstablishmentInputDTO(1L,
                FiltersEnum.DOG.getValue(),
                FiltersEnum.TINY.getValue(),
                FiltersEnum.CASTRATED.getValue(),
                FiltersEnum.MALE.getValue());
    }
    private EstablishmentInputDTO createSearchFilterWhitoutCityId() {
        return new EstablishmentInputDTO(null,
                FiltersEnum.DOG.getValue(),
                FiltersEnum.TINY.getValue(),
                FiltersEnum.CASTRATED.getValue(),
                FiltersEnum.MALE.getValue());
    }

    private Establishment createEstablishment() {

        Filters filter = new Filters(
                FiltersEnum.DOG.getValue(),
                FiltersEnum.TINY.getValue(),
                FiltersEnum.CASTRATED.getValue(),
                FiltersEnum.MALE.getValue());
        List<Filters> filters = new ArrayList<>();
        filters.add(filter);

        Establishment establishment = new Establishment("cnpj", "name", "description", "email", "numberPhone",
                new Address("street", "number", "complement", "district",
                        new City(1L, "Florianópolis", "SC"), "zipCode"),
                filters,
                "image test");
        establishment.setStatus(StatusEstablishment.ACTIVE);
        return establishment;
    }

}
