package com.upx.ticketsapi.util;

import java.util.List;

public class KeycloakUserDetails {
    private static final ThreadLocal<String> USER_EMAIL = new ThreadLocal<>();
    private static final ThreadLocal<List<String>> USER_ROLES = new ThreadLocal<>();

    private KeycloakUserDetails() {
        // empty
    }

    public static void setUserEmail(String email) {
        USER_EMAIL.set(email);
    }

    public static String getUserEmail() {
        return USER_EMAIL.get();
    }

    public static void clear() {
        USER_EMAIL.remove();
    }

    public static void setUserRoles(List<String> roles) {
        USER_ROLES.set(roles);
    }

    public static List<String> getUserRoles() {
        return USER_ROLES.get();
    }

    public static void clearRoles() {
        USER_ROLES.remove();
    }
}
