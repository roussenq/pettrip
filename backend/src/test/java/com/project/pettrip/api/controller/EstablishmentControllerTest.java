package com.project.pettrip.api.controller;
import com.project.pettrip.api.dto.AddressDTO;
import com.project.pettrip.api.dto.CityDTO;
import com.project.pettrip.api.dto.EstablishmentInputDTO;
import com.project.pettrip.api.dto.EstablishmentSummaryDTO;
import com.project.pettrip.domain.exception.BusinessException;
import com.project.pettrip.domain.service.CityService;
import com.project.pettrip.domain.service.EstablishmentService;
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
    private EstablishmentService establishmentService;

    @MockBean
    private CityService cityService;


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

    private EstablishmentSummaryDTO createEstablishmentSummaryDTO() {
        return new EstablishmentSummaryDTO(
                1L,
                "cnpj",
                "name",
                "description" ,
                "email",
                "number phone",
                "image", new AddressDTO(1L, "street", "number", "", "district",
                new CityDTO(1L, "Florianópolis", "SC"),"zip code" ));
    }
}