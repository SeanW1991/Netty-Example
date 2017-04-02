package com.sean.net.codec.handshake;

import com.sean.event.Event;

/**
 * Created by sean on 25/04/16.
 */
public class HandshakeEvent implements Event {

    private final HandshakeType type;

    public HandshakeEvent(HandshakeType type) {
        this.type = type;
    }

    public HandshakeType getType() {
        return type;
    }
}
