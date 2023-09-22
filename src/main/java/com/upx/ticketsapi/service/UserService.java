package com.upx.ticketsapi.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import static com.upx.ticketsapi.util.DTOConverterFactory.fromDTO;

import com.upx.ticketsapi.exception.HttpException;
import com.upx.ticketsapi.exception.NotFoundException;
import com.upx.ticketsapi.model.User;
import com.upx.ticketsapi.payload.UserDTO;
import com.upx.ticketsapi.payload.UserKeycloakDTO;
import com.upx.ticketsapi.repository.UserRepository;

@Service
public class UserService {
    private static final String USER_NOT_FOUND_MSG = "User with id %s";
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    public UserService(UserRepository userRepository, RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public User getById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
    }

    public User createUser(UserDTO userDto) {
        var user = fromDTO(userDto, User.class);
        user = userRepository.save(user);
        sendToQueue(user);
        return user;
    }

    public User editUser(UserDTO userDto) {
        var user = fromDTO(userDto, User.class);
        var userFromDb = getById(user.getUserId());
        BeanUtils.copyProperties(user, userFromDb, "userId", "active", "keycloakId");
        return userRepository.save(userFromDb);
    }

    public User updateStatus(Integer userId) {
        var user = getById(userId);
        user.setActive(!user.getActive());
        return userRepository.save(user);
    }

    public void sendToQueue(User user) {
        try {
            rabbitTemplate.convertAndSend("user-keycloak-queue", fromDTO(user, UserKeycloakDTO.class));
        } catch(Exception e) {
            throw new HttpException("Error while sending user to queue:" + e.getMessage());
        }
    }

    public User registerKeycloakId(Integer userId, String keycloakId) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setKeycloakId(keycloakId);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
    }
}
