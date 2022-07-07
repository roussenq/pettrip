package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.*;
import com.project.pettrip.domain.exception.BusinessException;
import com.project.pettrip.domain.model.*;
import com.project.pettrip.domain.repository.EstablishmentRepository;
import com.project.pettrip.domain.service.impl.EstablishmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class EstablishmentServiceImplTest {

    private IEstablishmentService establishmentService;

    @MockBean
    private EstablishmentRepository establishmentRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private IAddressService addressService;

    @MockBean
    private IFiltrerService filtrerService;

    @BeforeEach
    void setUp() {
        this.establishmentService = new EstablishmentServiceImpl(establishmentRepository, modelMapper, addressService, filtrerService);
    }

    @Test
    @DisplayName("Deve apresentar página com lista de estabelecimentos que possuem os filtros informados")
    void listByFiltersTest() {

        Page<Establishment> page = createPage();

        Mockito.when(establishmentRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(page);

        EstablishmentInputDTO searchFilters = createSearchFilter();
        searchFilters.setCityId(1L);

        Page<EstablishmentSummaryDTO> establishmentPage = establishmentService.findByFilters(searchFilters, page.getPageable());

        Assertions.assertNotNull(establishmentPage);
        Assertions.assertEquals(6, establishmentPage.getSize());
        Assertions.assertEquals(1, establishmentPage.getTotalPages());
        Assertions.assertEquals(2, establishmentPage.getTotalElements());
        Assertions.assertEquals(0, establishmentPage.getPageable().getPageNumber());
    }

    @Test
    @DisplayName("Deve apresentar página com lista de estabelecimentos em Florianópolis quando não houver id da cidade nos filtros")
    void listByFiltersWithoutCityTest() {

        Page<Establishment> page = createPage();

        Mockito.when(establishmentRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(page);

        BDDMockito.given(modelMapper.map(Mockito.any(), Mockito.any())).willReturn(createEstablishmentSummaryDTO());

        Page<EstablishmentSummaryDTO> establishmentPage = establishmentService.findByFilters(createSearchFilter(), page.getPageable());

        Assertions.assertNotNull(establishmentPage);
        Assertions.assertEquals(6, establishmentPage.getSize());
        Assertions.assertEquals(1, establishmentPage.getTotalPages());
        Assertions.assertEquals(2, establishmentPage.getTotalElements());
        Assertions.assertEquals(0, establishmentPage.getPageable().getPageNumber());
        Assertions.assertEquals("Florianópolis", establishmentPage.getContent().get(0).getAddress().getCity().getCity());
    }

    @Test
    @DisplayName("Deve apresentar erro quando não encontrar estabelecimentos com os filtros informados")
    void listByInexistentsFiltersTest() {
        Mockito.when(establishmentRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class)))
                .thenReturn(Page.empty());

        Assertions.assertThrows(BusinessException.class, () -> establishmentService.findByFilters(createSearchFilter(), PageRequest.of(0, 6)));
    }

    @Test
    @DisplayName("Deve criar um estabelecimento com sucesso.")
    public void createEstablishmentSuccessTest(){

        Establishment establishment = createEstablishment();
        Establishment establishmentReturn = establishment;
        establishmentReturn.setId(1L);
        establishmentReturn.getAddress().setId(1L);

        Mockito.when(establishmentRepository.save(establishment)).thenReturn(establishmentReturn);
        BDDMockito.given(modelMapper.map(Mockito.any(EstablishmentCompleteDTO.class), Mockito.any())).willReturn(establishment);
        BDDMockito.given(modelMapper.map(Mockito.any(Establishment.class), Mockito.any())).willReturn(createNewEstablishmentCompleteDTO());

        EstablishmentCompleteDTO establishmentSaved = establishmentService.create(createNewEstablishmentCompleteDTO());

        assertThat(establishmentSaved.getName()).isEqualTo("name test");
        assertThat(establishmentSaved.getCnpj()).isEqualTo("cnpj test");
        assertThat(establishmentSaved.getDescription()).isEqualTo("description test");
        assertThat(establishmentSaved.getEmail()).isEqualTo("email test");
        assertThat(establishmentSaved.getNumberPhone()).isEqualTo("number phone test");
        assertThat(establishmentSaved.getImage()).isEqualTo("image test");
        assertThat(establishmentSaved.getAddress().getStreet()).isEqualTo("street");
        assertThat(establishmentSaved.getAddress().getNumber()).isEqualTo("123");
        assertThat(establishmentSaved.getAddress().getComplement()).isEqualTo("");
        assertThat(establishmentSaved.getAddress().getDistrict()).isEqualTo("district");
        assertThat(establishmentSaved.getAddress().getZipCode()).isEqualTo("zip code");
        assertThat(establishmentSaved.getAddress().getCity().getCity()).isEqualTo("Florianópolis");
        assertThat(establishmentSaved.getAddress().getCity().getState()).isEqualTo("SC");
        assertThat(establishmentSaved.getFilters().get(0).getType()).isEqualTo(FiltersEnum.DOG.getValue());
        assertThat(establishmentSaved.getFilters().get(0).getWeight()).isEqualTo(FiltersEnum.TINY.getValue());
        assertThat(establishmentSaved.getFilters().get(0).getCastrated()).isEqualTo(FiltersEnum.CASTRATED.getValue());
        assertThat(establishmentSaved.getFilters().get(0).getGender()).isEqualTo(FiltersEnum.MALE.getValue());
    }

    @Test
    @DisplayName("Deve verificar os gets de Establishment")
    public void verifyGetsEstablishmentTest(){
        Establishment establishment = createEstablishment();
        establishment.setCreatedAt(OffsetDateTime.now());
        establishment.setUpdatedAt(OffsetDateTime.now());

        Address address = createAddress();
        List<Filters> filters = createFilters();

        Assertions.assertEquals("cnpj test", establishment.getCnpj());
        Assertions.assertEquals("name test", establishment.getName());
        Assertions.assertEquals("description test", establishment.getDescription());
        Assertions.assertEquals("email test", establishment.getEmail());
        Assertions.assertEquals(StatusEstablishment.ACTIVE, establishment.getStatus());
        Assertions.assertEquals("image test", establishment.getImage());
        Assertions.assertEquals("number phone test", establishment.getNumberPhone());
        Assertions.assertFalse(establishment.getCreatedAt().isAfter(OffsetDateTime.now()));
        Assertions.assertFalse(establishment.getUpdatedAt().isAfter(OffsetDateTime.now()));
        Assertions.assertEquals(address.getId(), establishment.getAddress().getId());
        Assertions.assertEquals(filters.get(0).getId(), establishment.getFilters().get(0).getId());
    }

    @Test
    @DisplayName("Deve retornar erro quando tentar cadastrar estabelecimento com CNPJ existente")
    public void duplicatedCNPJTest(){
        Establishment establishment = createEstablishment();
        BDDMockito.given(modelMapper.map(Mockito.any(EstablishmentCompleteDTO.class), Mockito.any())).willReturn(establishment);
        Mockito.when(establishmentRepository.findByCnpj(Mockito.anyString())).thenThrow(BusinessException.class);

        Assertions.assertThrows(BusinessException.class, () -> establishmentService.create(createNewEstablishmentCompleteDTO()));
        Mockito.verify(establishmentRepository, Mockito.never()).save(establishment);
    }

    @Test
    @DisplayName("Deve verificar que dois estabelecimentos não são iguais.")
    public void notEqualsEstablishmentTest(){
        Establishment establishment = createEstablishment();
        establishment.setId(1L);
        Establishment establishment2 = createEstablishment();
        establishment2.setId(2L);
        establishment2.setCnpj("outro snpj");

        Assertions.assertNotEquals(establishment, establishment2);
    }

    @Test
    @DisplayName("Deve deletar um estabelecimento com sucesso")
    public void deleteEstablishmentTest(){
        Establishment establishment = createEstablishment();
        establishment.setId(1L);

        Assertions.assertDoesNotThrow(() -> establishmentService.delete(establishment));

        Mockito.verify(establishmentRepository, Mockito.times(1)).delete(establishment);
    }

    @Test
    @DisplayName("Deve ocorrer erro ao tentar deletar um estabelecimento")
    public void deleteInvalidEstablishmentTest(){
        Establishment establishment = createEstablishment();

        Assertions.assertThrows(IllegalArgumentException.class, () -> establishmentService.delete(establishment));

        Mockito.verify(establishmentRepository, Mockito.never()).delete(establishment);
    }

    private PageImpl<Establishment> createPage() {
        Establishment establishment = createEstablishment();
        List<Establishment> listEstablishment = Arrays.asList(establishment, establishment);
        Pageable pageRequest = PageRequest.of(0, 6);
        return new PageImpl<>(listEstablishment, pageRequest, 1);
    }


    private EstablishmentInputDTO createSearchFilter() {
        EstablishmentInputDTO establishmentInputDTO = new EstablishmentInputDTO();
        establishmentInputDTO.setType(FiltersEnum.DOG.getValue());
        establishmentInputDTO.setWeight(FiltersEnum.TINY.getValue());
        establishmentInputDTO.setCastrated(FiltersEnum.CASTRATED.getValue());
        establishmentInputDTO.setGender(FiltersEnum.MALE.getValue());
        return establishmentInputDTO;
    }

    private Establishment createEstablishment() {

        Establishment establishment = new Establishment();
        establishment.setCnpj("cnpj test");
        establishment.setName("name test");
        establishment.setDescription("description test");
        establishment.setEmail("email test");
        establishment.setNumberPhone("number phone test");
        establishment.setImage("image test");
        establishment.setAddress(createAddress());
        establishment.setFilters(createFilters());
        establishment.setStatus(StatusEstablishment.ACTIVE);
        return establishment;
    }

    private Address createAddress() {
        Address address = new Address();
        address.setStreet("street");
        address.setNumber("123");
        address.setComplement("");
        address.setDistrict("district");
        address.setZipCode("zip code");
        address.setCity(createCity());
        return address;
    }

    private City createCity() {
        City city = new City();
        city.setId(1L);
        city.setCity("Florianópolis");
        city.setState("SC");
        return city;
    }

    private List<Filters> createFilters() {
        Filters filter = new Filters();
        filter.setType(FiltersEnum.DOG.getValue());
        filter.setWeight(FiltersEnum.TINY.getValue());
        filter.setCastrated(FiltersEnum.CASTRATED.getValue());
        filter.setGender(FiltersEnum.MALE.getValue());
        List<Filters> filters = new ArrayList<>();
        filters.add(filter);
        return filters;
    }

    private EstablishmentSummaryDTO createEstablishmentSummaryDTO() {

        EstablishmentSummaryDTO establishmentSummaryDTO = new EstablishmentSummaryDTO();
        establishmentSummaryDTO.setId(1L);
        establishmentSummaryDTO.setCnpj("cnpj test");
        establishmentSummaryDTO.setName("name test");
        establishmentSummaryDTO.setDescription("description test");
        establishmentSummaryDTO.setEmail("email test");
        establishmentSummaryDTO.setNumberPhone("number phone test");
        establishmentSummaryDTO.setImage("image test");
        establishmentSummaryDTO.setAddress(createAddressDTO());

        return establishmentSummaryDTO;
    }

    private AddressDTO createAddressDTO() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(1L);
        addressDTO.setStreet("street");
        addressDTO.setNumber("123");
        addressDTO.setComplement("");
        addressDTO.setDistrict("district");
        addressDTO.setZipCode("zip code");
        addressDTO.setCity(createCityDTO());
        return addressDTO;
    }

    private CityDTO createCityDTO() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(1L);
        cityDTO.setCity("Florianópolis");
        cityDTO.setState("SC");
        return cityDTO;
    }

    private EstablishmentCompleteDTO createNewEstablishmentCompleteDTO() {
        EstablishmentCompleteDTO establishmentCompleteDTO = new EstablishmentCompleteDTO();
        establishmentCompleteDTO.setCnpj("cnpj test");
        establishmentCompleteDTO.setName("name test");
        establishmentCompleteDTO.setDescription("description test");
        establishmentCompleteDTO.setEmail("email test");
        establishmentCompleteDTO.setNumberPhone("number phone test");
        establishmentCompleteDTO.setImage("image test");
        establishmentCompleteDTO.setAddress(createAddressInputDTO());
        establishmentCompleteDTO.setFilters(createFilterDTO());

        return establishmentCompleteDTO;
    }

    private AddressInputDTO createAddressInputDTO() {
        AddressInputDTO addressInputDTO = new AddressInputDTO();
        addressInputDTO.setStreet("street");
        addressInputDTO.setNumber("123");
        addressInputDTO.setComplement("");
        addressInputDTO.setDistrict("district");
        addressInputDTO.setZipCode("zip code");
        addressInputDTO.setCity(createCityInputDTO());
        return addressInputDTO;
    }

    private CityInputDTO createCityInputDTO() {
        CityInputDTO cityInputDTO = new CityInputDTO();
        cityInputDTO.setCity("Florianópolis");
        cityInputDTO.setState("SC");
        return cityInputDTO;
    }
    private List<FilterDTO> createFilterDTO() {
        FilterDTO filter = new FilterDTO();
        filter.setType(FiltersEnum.DOG.getValue());
        filter.setWeight(FiltersEnum.TINY.getValue());
        filter.setCastrated(FiltersEnum.CASTRATED.getValue());
        filter.setGender(FiltersEnum.MALE.getValue());
        List<FilterDTO> filters = new ArrayList<>();
        filters.add(filter);
        return filters;
    }


}
