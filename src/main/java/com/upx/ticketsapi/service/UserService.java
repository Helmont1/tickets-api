package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.User;
import com.upx.ticketsapi.payload.UserDTO;
import com.upx.ticketsapi.repository.UserRepository;

@Service
public class UserService {
    private static final String USER_NOT_FOUND_MSG = "User with id %s";
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User fromDTO(UserDTO dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
