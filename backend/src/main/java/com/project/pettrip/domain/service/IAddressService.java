package com.project.pettrip.domain.service;

import com.project.pettrip.api.dto.AddressInputDTO;
import com.project.pettrip.domain.model.Address;

/**
 * Interface para gerenciamento de endereços.
 */
public interface IAddressService {

    /**
     * Método para criação de endereço.
     *
     * @param address do tipo AddressInputDTO.
     * @return entidade Address salvo.
     */
    Address create(AddressInputDTO address);
}
