package com.upx.ticketsapi.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class QueueRoutines {
    private final UserService userService;

    public QueueRoutines( UserService userService) {
        this.userService = userService;
    }

    @Scheduled(fixedRate = 1500000)
    public void checkIfUsersNeedToGoToQueue() {
        try {
            userService.getAllUsers().forEach(user -> {
                if (user.getKeycloakId() == null) {
                    userService.sendToQueue(user);
                }
            });
        } catch(Exception ignored) {
            // on purpose
        }
    }   
}
