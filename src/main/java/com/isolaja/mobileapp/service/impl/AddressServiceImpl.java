package com.isolaja.mobileapp.service.impl;

import com.isolaja.mobileapp.io.entity.AddressEntity;
import com.isolaja.mobileapp.io.entity.UserEntity;
import com.isolaja.mobileapp.io.repository.AddressRepository;
import com.isolaja.mobileapp.io.repository.UserRepository;
import com.isolaja.mobileapp.service.AddressService;
import com.isolaja.mobileapp.shared.dto.AddressDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    public AddressServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> getAddress(String userId) {
        List<AddressDTO> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) return returnValue;

        Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
        for (AddressEntity addressEntity : addresses) {
            returnValue.add(modelMapper.map(addressEntity, AddressDTO.class));
        }

        return returnValue;
    }
}
