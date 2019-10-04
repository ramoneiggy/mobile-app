package com.isolaja.mobileapp.service;

import com.isolaja.mobileapp.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
    UserDto updateUser(String userId, UserDto userDto);
    void deleteUser(String userId, String authorization);
    List<UserDto> getUsers(int page, int limit);

    boolean verifyEmailToken(String token);
}
