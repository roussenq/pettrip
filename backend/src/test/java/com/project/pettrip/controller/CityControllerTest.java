package com.project.pettrip.controller;

import com.project.pettrip.model.City;
import com.project.pettrip.service.CityService;
import com.project.pettrip.service.EstablishmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
class CityControllerTest {

    static String CITY_URI = "/city";

    @Autowired
    MockMvc mvc;

    @MockBean
    private CityService cityService;

    @MockBean
    private EstablishmentService establishmentService;


    @Test
    @DisplayName("Deve listar as cidades existentes no banco de dados")
    void listCities() throws Exception {

        City city = new City(1L, "Florianópolis", "SC");

        BDDMockito.given(cityService.listCities()).willReturn(List.of(city));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(CITY_URI)
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(city.getId()))));
    }

}