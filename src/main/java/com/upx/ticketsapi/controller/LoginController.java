package com.upx.ticketsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upx.ticketsapi.config.response.LoginResponse;
import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.model.LoginRequest;
import com.upx.ticketsapi.model.User;
import com.upx.ticketsapi.service.LoginService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @GetMapping("/logged")
    public ResponseEntity<User> test() {
        return loginService.loggedUser();
    }

    @GetMapping("/roles")
    public ResponseEntity<SuccessResponse<List<String>>> getRoles() {
        return loginService.getRoles();
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody String refreshToken) {
        return loginService.refreshToken(refreshToken);
    }
}
