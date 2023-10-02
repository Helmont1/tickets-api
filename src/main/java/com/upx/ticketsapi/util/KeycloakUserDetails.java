package com.upx.ticketsapi.util;


public class KeycloakUserDetails {
    private static final ThreadLocal<String> USER_EMAIL = new ThreadLocal<>();

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
}
