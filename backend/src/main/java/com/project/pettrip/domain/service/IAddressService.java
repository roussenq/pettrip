package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.AddressInputDTO;
import com.project.pettrip.domain.model.Address;

public interface IAddressService {

    Address create(AddressInputDTO address);
}
