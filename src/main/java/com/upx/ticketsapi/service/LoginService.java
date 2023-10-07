package com.upx.ticketsapi.service;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.upx.ticketsapi.config.response.LoginResponse;
import com.upx.ticketsapi.config.response.SuccessResponse;
import com.upx.ticketsapi.exception.HttpException;
import com.upx.ticketsapi.model.LoginRequest;
import com.upx.ticketsapi.model.User;
import com.upx.ticketsapi.util.KeycloakUserDetails;
import com.upx.ticketsapi.util.SuccessResponseUtil;

@Service
public class LoginService {
    private final RestTemplate restTemplate;
    private final UserService userService;

    private String issuerUrl;
    private String clientId;
    private String clientSecret;

    public LoginService(RestTemplate restTemplate, Environment env, UserService userService) {
        this.restTemplate = restTemplate;
        this.issuerUrl = env.getProperty("spring.security.oauth2.client.provider.keycloak.issuer-uri");
        this.clientId = env.getProperty("spring.security.oauth2.client.registration.keycloak.client-id");
        this.clientSecret = env.getProperty("spring.security.oauth2.client.registration.keycloak.client-secret");
        this.userService = userService;
    }

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        var headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("client_id", clientId);
        map.add("client_secret", clientSecret);
        map.add("grant_type", "password");
        map.add("username", loginRequest.getUsername());
        map.add("password", loginRequest.getPassword());

        var entity = new HttpEntity<>(map, headers);
        try {
            var response = restTemplate.postForEntity(issuerUrl + "/protocol/openid-connect/token", entity, LoginResponse.class);
            return new ResponseEntity<>(
                response.getBody(), HttpStatus.OK
            );

        } catch(Exception e) {
            throw new HttpException("Error while trying to login" + e.getMessage());
        } 
    }

    public ResponseEntity<User> loggedUser() {
        var user = userService.getLoggedUser();
        return new ResponseEntity<>(
            user, HttpStatus.OK
        );
    }

    public ResponseEntity<SuccessResponse<List<String>>> getRoles() {
        return SuccessResponseUtil.okResponse(KeycloakUserDetails.getUserRoles());
    }

    
}
