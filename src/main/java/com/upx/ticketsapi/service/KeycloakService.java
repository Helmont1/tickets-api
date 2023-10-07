package com.upx.ticketsapi.service;

import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {
    private final Keycloak keycloak;

    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public List<UserRepresentation> getAnalystsInRealm() {
        return keycloak.realm("upx-tickets").users()
                .list()
                .stream()
                .filter(user -> user.getRealmRoles() != null && user.getRealmRoles().contains("analyst"))
                .collect(Collectors.toList());
    }

    public List<UserRepresentation> getRequestersInRealm() {
        return keycloak.realm("upx-tickets").users()
                .list().stream()
                .filter(user -> user.getRealmRoles() != null ? user.getRealmRoles().contains("requester") : false)
                .collect(Collectors.toList());
    }
}
