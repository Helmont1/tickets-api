package com.upx.ticketsapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {
    private final Keycloak keycloak;

    public KeycloakService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public List<UserRepresentation> getByRole(String role) {

        var usersList = keycloak.realm("upx-tickets").users()
                .list()
                .stream()
                .collect(Collectors.toList());

        for (var userRepresentation : usersList) {
            var userResource = keycloak.realm("upx-tickets")
                    .users().get(userRepresentation.getId());
            userRepresentation.setRealmRoles(userResource.roles().getAll().getRealmMappings().stream().map(RoleRepresentation::getName).collect(Collectors.toList()));
            userRepresentation.setGroups(userResource.groups().stream().map(GroupRepresentation::getName).collect(Collectors.toList()));
        }

        return usersList.stream()
                .filter(user -> user.getRealmRoles() != null && user.getRealmRoles().contains(role))
                .collect(Collectors.toList());
    }
}
