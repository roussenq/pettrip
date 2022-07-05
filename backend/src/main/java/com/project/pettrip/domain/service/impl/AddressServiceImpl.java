package com.project.pettrip.domain.service.impl;

import com.project.pettrip.api.dto.AddressInputDTO;
import com.project.pettrip.api.dto.CityInputDTO;
import com.project.pettrip.domain.model.Address;
import com.project.pettrip.domain.model.City;
import com.project.pettrip.domain.repository.AddressRepository;
import com.project.pettrip.domain.service.IAddressService;
import com.project.pettrip.domain.service.ICityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe da camada Service com a lógica de implementação referênte à endereços.
 */
@Service
public class AddressServiceImpl implements IAddressService {

    private final AddressRepository addressRepository;
    private final ICityService cityService;
    private final ModelMapper modelMapper;

    /**
     * Construtor de instanciação de AddressServiceImpl.
     *
     * @param addressRepository  referência à Interface AddressRepository.
     * @param modelMapper        referência ModelMapper.
     * @param cityService        referência à Interface ICityService.
     */
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper, ICityService cityService) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
    }


    /**
     * Cria um endereço observando se a cidade já existe no banco de dados.
     * Caso exista: utiliza a cidade existente,
     * Caso não exista: cria uma nova cidade.
     *
     * @param addressDTO do tipo AddressInputDTO.
     * @return entidade Address.
     * @override interface IAdressService.
     */
    @Transactional
    @Override
    public Address create(AddressInputDTO addressDTO) {
        City city = cityService.findByCity(addressDTO.getCity().getCity());
        Address address = toAddressEntity(addressDTO);
        if(city != null)
            address.setCity(city);
        else
            address.setCity(cityService.create(toCityEntity(addressDTO.getCity())));

        return addressRepository.save(address);
    }

    /**
     * Método para conversão de AddressInputDTO para entidade Address
     *
     * @param dto AddressInputDTO.
     * @return entidade Address.
     */
    private Address toAddressEntity(AddressInputDTO dto){
        return modelMapper.map(dto, Address.class);
    }

    /**
     * Método para conversão de CityInputDTO para entidade City
     *
     * @param dto CityInputDTO.
     * @return entidade City.
     */
    private City toCityEntity(CityInputDTO dto){
        return modelMapper.map(dto, City.class);
    }

}
