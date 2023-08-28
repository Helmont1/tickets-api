package com.upx.ticketsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.User;
import com.upx.ticketsapi.service.UserService;
import com.upx.ticketsapi.util.SuccessResponseUtil;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SuccessResponse<User>> getOne(@PathVariable Integer userId) {
        var user = userService.getById(userId);
        return SuccessResponseUtil.okResponse(user);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<User>> create(
            @RequestBody User user) {
        return SuccessResponseUtil.createdResponse(userService.createUser(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<SuccessResponse<User>> update(
            @RequestBody User user) {
        return SuccessResponseUtil.okResponse(userService.editUser(user));

    }

    @PutMapping("/status/{userId}")
    public ResponseEntity<SuccessResponse<User>> updateStatus(
            @PathVariable Integer userId) {
        return SuccessResponseUtil.okResponse(userService.updateStatus(userId));
    }

}
