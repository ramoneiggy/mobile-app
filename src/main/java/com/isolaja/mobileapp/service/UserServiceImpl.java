package com.isolaja.mobileapp.service;

import com.isolaja.mobileapp.exceptions.UserServiceException;
import com.isolaja.mobileapp.io.entity.UserEntity;
import com.isolaja.mobileapp.io.repository.UserRepository;
import com.isolaja.mobileapp.shared.JwtUtil;
import com.isolaja.mobileapp.shared.Utils;
import com.isolaja.mobileapp.shared.dto.UserDto;
import com.isolaja.mobileapp.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Utils utils;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Record already exists");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setUserId(utils.generateUserId(30));
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UserServiceException("User with ID " + userId + " not found!");
        }
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updatedUserDetails = userRepository.save(userEntity);

        BeanUtils.copyProperties(updatedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public void deleteUser(String userId, String authorizationHeader) {

        // check if user is trying to delete another user
        String userIdFromHeader = jwtUtil.getUserId(authorizationHeader);
        if (!userId.equalsIgnoreCase(userIdFromHeader)) {
            throw new UserServiceException(ErrorMessages.OPERATION_NOT_ALLOWED.getErrorMessage());
        }

        // delete user
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        userRepository.delete(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
