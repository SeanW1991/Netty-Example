package com.sean.net.codec.handshake.impl;

import com.sean.net.codec.handshake.HandshakeEvent;
import com.sean.net.codec.handshake.HandshakeType;
import io.netty.buffer.ByteBuf;

/**
 * Created by sean on 26/04/16.
 */
public abstract class HandshakeRequestEvent<T> extends HandshakeEvent {

    protected final ByteBuf payload;

    public HandshakeRequestEvent(HandshakeType type, ByteBuf payload) {
        super(type);
        this.payload = payload;
    }

    public abstract void addToBuffer(T obj);

    public ByteBuf getPayload() {
        return payload;
    }
}
