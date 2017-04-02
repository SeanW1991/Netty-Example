package com.sean.net.codec.authentication;

import com.sean.event.Event;
import io.netty.buffer.ByteBuf;

/**
 * Created by sean on 26/04/16.
 */
public final class AuthenticationResponseEvent implements Event {

    private final AuthenticationResponseType type;

    private final ByteBuf payload;

    private AuthenticationResponseEvent(AuthenticationResponseType type, ByteBuf payload) {
        this.type = type;
        this.payload = payload;
    }

    public AuthenticationResponseEvent(AuthenticationResponseType type) {
        this(type, null);
    }

    public AuthenticationResponseEvent(ByteBuf payload) {
        this(AuthenticationResponseType.AUTHENTICATION_OK, payload);
    }

    public AuthenticationResponseType getType() {
        return type;
    }

    public ByteBuf getPayload() {
        return payload;
    }
}
