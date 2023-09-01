package com.upx.ticketsapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

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

    public User getById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
    }

    public User createUser(UserDTO userDto) {
        var user = fromDTO(userDto, User.class);
        return userRepository.save(user);
    }

    public User editUser(UserDTO userDto) {
        var user = fromDTO(userDto, User.class);
        var userFromDb = getById(user.getUserId());
        BeanUtils.copyProperties(user, userFromDb, "userId");
        return userRepository.save(userFromDb);
    }

    public User updateStatus(Integer userId) {
        var user = getById(userId);
        user.setActive(!user.getActive());
        return userRepository.save(user);
    }

}
