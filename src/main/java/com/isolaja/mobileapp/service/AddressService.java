package com.isolaja.mobileapp.service;

import com.isolaja.mobileapp.shared.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> getAddresses(String userId);

    AddressDTO getAddress(String addressId);
}
