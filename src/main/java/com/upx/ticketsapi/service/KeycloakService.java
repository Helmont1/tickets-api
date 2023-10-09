package com.upx.ticketsapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class KeycloakService {
    private final Keycloak keycloak;
    private final String realm;

    public KeycloakService(Keycloak keycloak, Environment env) {
        this.keycloak = keycloak;
        this.realm = env.getProperty("keycloak.realm");
    }

    public List<UserRepresentation> getByRole(String role) {

        var usersList = keycloak.realm(realm).users()
                .list()
                .stream()
                .collect(Collectors.toList());

        for (var userRepresentation : usersList) {
            var userResource = keycloak.realm(realm)
                    .users().get(userRepresentation.getId());
            userRepresentation.setRealmRoles(userResource.roles().getAll().getRealmMappings().stream().map(RoleRepresentation::getName).collect(Collectors.toList()));
            userRepresentation.setGroups(userResource.groups().stream().map(GroupRepresentation::getName).collect(Collectors.toList()));
        }

        return usersList.stream()
                .filter(user -> user.getRealmRoles() != null && user.getRealmRoles().contains(role))
                .collect(Collectors.toList());
    }

}
