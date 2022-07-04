package com.project.pettrip.domain.service.impl;

import com.project.pettrip.api.dto.AddressInputDTO;
import com.project.pettrip.api.dto.CityInputDTO;
import com.project.pettrip.domain.model.Address;
import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.repository.AddressRepository;
import com.project.pettrip.domain.repository.CityRepository;
import com.project.pettrip.domain.service.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl implements IAddressService {

    private AddressRepository addressRepository;

    private CityRepository cityRepository;
    private ModelMapper modelMapper;

    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    /**
     * @param addressDTO
     */
    @Transactional
    @Override
    public Address create(AddressInputDTO addressDTO) {
        City city = cityRepository.findByCity(addressDTO.getCity().getCity());
        Address address = toAddressEntity(addressDTO);
        if(city != null)
            address.setCity(city);
        else
            address.setCity(cityRepository.save(toCityEntity(addressDTO.getCity())));

        return addressRepository.save(address);
    }
    private Address toAddressEntity(AddressInputDTO dto){
        return modelMapper.map(dto, Address.class);
    }

    private City toCityEntity(CityInputDTO dto){
        return modelMapper.map(dto, City.class);
    }

}
