package com.sean.net.codec.authentication;

import com.sean.event.impl.DefaultEventListener;

/**
 * Created by sean on 26/04/16.
 */
public final class AuthenticationEventListener implements DefaultEventListener<AuthenticationRequestEvent, AuthenticationEventContext> {

    @Override
    public EventStage onEvent(AuthenticationRequestEvent event, AuthenticationEventContext context) {
        System.out.println(event.toString() + " " + context);
        return EventStage.CONTINUE;
    }
}
