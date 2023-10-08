package com.upx.ticketsapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.User;
import com.upx.ticketsapi.payload.UserDTO;
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
            @RequestBody UserDTO user) {
        return SuccessResponseUtil.createdResponse(userService.createUser(user));
    }

    @PostMapping("/keycloak")
    public ResponseEntity<SuccessResponse<User>> registerKeycloakId(
            @RequestBody Integer userId,
            @RequestBody String keycloakId) {
        return SuccessResponseUtil.okResponse(userService.registerKeycloakId(userId, keycloakId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<SuccessResponse<User>> update(
            @RequestBody UserDTO user) {
        return SuccessResponseUtil.okResponse(userService.editUser(user));
    }

    @PutMapping("/status/{userId}")
    public ResponseEntity<SuccessResponse<User>> updateStatus(
            @PathVariable Integer userId) {
        return SuccessResponseUtil.okResponse(userService.updateStatus(userId));
    }

    @GetMapping("/logged")
    public ResponseEntity<SuccessResponse<User>> getLoggedUser() {
        return SuccessResponseUtil.okResponse(userService.getLoggedUser());
    }

    @GetMapping("/analysts/")
    public ResponseEntity<SuccessResponse<Page<User>>> getAnalysts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "userId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        return SuccessResponseUtil.okResponse(userService.getAnalysts(
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy))));
    }

    @GetMapping("/requesters/")
    public ResponseEntity<SuccessResponse<Page<User>>> getRequesters(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "userId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        return SuccessResponseUtil.okResponse(userService.getRequesters(
                PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sortBy))));
    }
}
