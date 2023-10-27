package com.upx.ticketsapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.admin.client.Keycloak;
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
        var users = keycloak.realm(realm).roles()
                .get(role).getUserMembers();

        keycloak.realm(realm).roles()
                .get(role).getRoleGroupMembers()
                .forEach(group -> users.addAll(keycloak.realm(realm).groups().group(group.getId()).members()));

        return users.stream().distinct().collect(Collectors.toList());
    }

}
