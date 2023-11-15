package com.upx.ticketsapi.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class QueueRoutines {
    private final UserService userService;
    private final TeamUserService teamUserService;

    public QueueRoutines(UserService userService, TeamUserService teamUserService) {
        this.userService = userService;
        this.teamUserService = teamUserService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void checkIfUsersNeedToGoToQueue() {
        try {
            userService.getToQueue().forEach(userService::sendToQueue);
        } catch(Exception ignored) {
            // on purpose
        }
    } 
    
    // @Scheduled(cron = "0 0 0 * * *")
    // public void calculateTicket
}
