package com.project.pettrip.api.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pettrip.api.dto.*;
import com.project.pettrip.domain.exception.BusinessException;
import com.project.pettrip.domain.service.ICityService;
import com.project.pettrip.domain.service.IEstablishmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
class EstablishmentControllerTest {

    static String ESTABLISHMENT_URI = "/establishment";

    @Autowired
    MockMvc mvc;

    @MockBean
    private IEstablishmentService establishmentService;

    @MockBean
    private ICityService cityService;


    @Test
    @DisplayName("Deve listar estabelecimentos com filtros encontrados.")
    void findWithFilters() throws Exception {

        EstablishmentSummaryDTO establishmentSummaryDTO = createEstablishmentSummaryDTO();

        BDDMockito.given(establishmentService.findByFilters(Mockito.any(EstablishmentInputDTO.class), Mockito.any(Pageable.class)))
                .willReturn(new PageImpl<EstablishmentSummaryDTO>(Arrays.asList(establishmentSummaryDTO), PageRequest.of(0, 6), 1));

        String queryString = String.format("?cityId=%s&type=%s&weight=%s&castrated=%s&gender=%s&page=0&size=6",
                                                    1L, "cat", "tiny", "castrated", "male");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(ESTABLISHMENT_URI.concat(queryString))
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("content", hasSize(1)))
                .andExpect(jsonPath("totalElements").value(1))
                .andExpect(jsonPath("pageable.pageSize").value(6))
                .andExpect(jsonPath("pageable.pageNumber").value(0));
    }

    @Test
    @DisplayName("Deve listar estabelecimentos localizados em Florianópolis/SC quando não for informada a cidade.")
    void findWithoutCityId() throws Exception {

        EstablishmentSummaryDTO establishmentSummaryDTO = createEstablishmentSummaryDTO();

        BDDMockito.given(establishmentService.findByFilters(Mockito.any(EstablishmentInputDTO.class), Mockito.any(Pageable.class)))
                .willReturn(new PageImpl<EstablishmentSummaryDTO>(Arrays.asList(establishmentSummaryDTO), PageRequest.of(0, 6), 1));

        String queryString = String.format("?type=%s&weight=%s&castrated=%s&gender=%s&page=0&size=6",
                "cat", "tiny", "castrated", "male");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(ESTABLISHMENT_URI.concat(queryString))
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("content", hasSize(1)))
                .andExpect(jsonPath("totalElements").value(1))
                .andExpect(jsonPath("pageable.pageSize").value(6))
                .andExpect(jsonPath("pageable.pageNumber").value(0));
    }

    @Test
    @DisplayName("Deve retornar erro quando não houverem resultados com os filtros desejados.")
    void findWithoutFilters() throws Exception {
        String mensageError = "Oops... Sinto muito. Não foi possível encontrar resultados com as informações " +
                "que você deseja, tente novamente com uma localização próxima ao destino.";


        BDDMockito.given(establishmentService.findByFilters(Mockito.any(EstablishmentInputDTO.class), Mockito.any(Pageable.class)))
                .willThrow(new BusinessException(mensageError));

        String queryString = String.format("?cityId=%s&type=%s&weight=%s&castrated=%s&gender=%s&page=0&size=6",
                1L, "cat", "tiny", "castrated", "male");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(ESTABLISHMENT_URI.concat(queryString))
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("title").value(mensageError));
    }

    @Test
    @DisplayName("Deve criar um estabelecimento com sucesso.")
    public void createEstablishment() throws Exception {
        EstablishmentCompleteDTO establishmentCompleteDTO = createNewEstablishmentCompleteDTO();

        EstablishmentCompleteDTO savedEstablishment = createNewEstablishmentCompleteDTO();

        BDDMockito.given(establishmentService.create(Mockito.any(EstablishmentCompleteDTO.class))).willReturn(savedEstablishment);
        String json = new ObjectMapper().writeValueAsString(establishmentCompleteDTO);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ESTABLISHMENT_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(2L))
                .andExpect(jsonPath("name").value(establishmentCompleteDTO.getName()));

    }

    @Test
    @DisplayName("Deve deletar um estabelecimento com o id informado.")
    void deleteEstablishment() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(ESTABLISHMENT_URI.concat("/1"));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve retornar erro quando não houver estabelecimento com o id informado.")
    void deleteInvalidEstablishment() throws Exception {

        BDDMockito.given(establishmentService.findEstablishementById(Mockito.anyLong())).willThrow(EntityNotFoundException.class);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(ESTABLISHMENT_URI.concat("/88"));

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("")
    void inactivateEstablishment() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(ESTABLISHMENT_URI.concat("/1/inactivate"));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("")
    void activateEstablishment() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(ESTABLISHMENT_URI.concat("/1/activate"));

        mvc.perform(request)
                .andExpect(status().isNoContent());
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
        establishmentCompleteDTO.setId(2L);
        establishmentCompleteDTO.setCnpj("cnpj test");
        establishmentCompleteDTO.setName("name test");
        establishmentCompleteDTO.setDescription("description test");
        establishmentCompleteDTO.setEmail("email test");
        establishmentCompleteDTO.setNumberPhone("number phone test");
        establishmentCompleteDTO.setImage("image test");
        establishmentCompleteDTO.setAddress(createAddressInputDTO());

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
}