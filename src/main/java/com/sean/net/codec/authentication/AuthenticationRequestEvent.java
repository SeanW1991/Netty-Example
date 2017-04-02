package com.sean.net.codec.authentication;

import com.google.common.base.MoreObjects;
import com.sean.event.Event;

import java.util.Objects;

/**
 * Created by sean on 26/04/16.
 */
public class AuthenticationRequestEvent implements Event {

    private final String username;

    private final String password;

    public AuthenticationRequestEvent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(AuthenticationRequestEvent.class)
                .add("username", username)
                .add("password", password)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
