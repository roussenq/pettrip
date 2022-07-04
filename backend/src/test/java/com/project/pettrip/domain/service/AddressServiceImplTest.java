package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.*;
import com.project.pettrip.domain.model.Address;
import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.repository.AddressRepository;
import com.project.pettrip.domain.repository.CityRepository;
import com.project.pettrip.domain.service.impl.AddressServiceImpl;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AddressServiceImplTest {

    private IAddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        this.addressService = new AddressServiceImpl(addressRepository, modelMapper, cityRepository);
    }

    @Test
    @DisplayName("Deve criar um endereço com cidade existente sucesso")
    void createAddressWithExistentCity() {
        Address address = createAddressTest();
        Address addressReturn = address;
        City city = createCity();
        addressReturn.setId(1L);

        BDDMockito.given(cityRepository.findByCity(Mockito.anyString())).willReturn(city);
        BDDMockito.given(modelMapper.map(Mockito.any(AddressInputDTO.class), Mockito.any())).willReturn(address);
        BDDMockito.given(addressRepository.save(address)).willReturn(addressReturn);

        Address savedAddress = addressService.create(createAddressInputDTO());

        assertThat(savedAddress.getId()).isNotNull();
        Mockito.verify(addressRepository, Mockito.times(1)).save(address);
    }

    @Test
    @DisplayName("Deve criar um endereço sem cidade existente sucesso")
    void createAddressWithoutExistentCityTest() {
        Address address = createAddressTest();
        Address addressReturn = address;
        City city = createCity();
        City citySaved = city;
        citySaved.setId(1L);
        addressReturn.setId(1L);

        BDDMockito.given(cityRepository.findByCity(Mockito.anyString())).willReturn(null);
        BDDMockito.given(modelMapper.map(Mockito.any(AddressInputDTO.class), Mockito.any())).willReturn(address);
        BDDMockito.given(modelMapper.map(Mockito.any(CityInputDTO.class), Mockito.any())).willReturn(city);
        BDDMockito.given(cityRepository.save(Mockito.any(City.class))).willReturn(citySaved);

        addressService.create(createAddressInputDTO());

        Mockito.verify(cityRepository, Mockito.times(1)).save(city);
        Mockito.verify(addressRepository, Mockito.times(1)).save(address);
    }

    @Test
    @DisplayName("Deve verificar os gets de Address")
    void verifyGetsAddressTest() {
        Address address = createAddressTest();
        address.setId(1L);
        City city = createCity();
        city.setId(1L);

        Assertions.assertEquals(1L, address.getId());
        Assertions.assertEquals("street", address.getStreet());
        Assertions.assertEquals("123", address.getNumber());
        Assertions.assertEquals("", address.getComplement());
        Assertions.assertEquals("district", address.getDistrict());
        Assertions.assertEquals("zip code", address.getZipCode());
        Assertions.assertEquals(city.getCity(), address.getCity().getCity());
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


    private Address createAddressTest() {
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
        city.setCity("Florianópolis");
        city.setState("SC");
        return city;
    }
}