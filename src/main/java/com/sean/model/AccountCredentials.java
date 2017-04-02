package com.sean.model;

/**
 * Created by sean on 26/04/16.
 */
public final class AccountCredentials {

    private final String username;

    private final String password;

    public AccountCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
