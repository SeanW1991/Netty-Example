package com.sean.net.codec.handshake.impl;

/**
 * Created by sean on 01/05/2016.
 */
public final class HandshakeResponseEvent {

    private final long fingerprint;

    public HandshakeResponseEvent(long fingerprint) {
        this.fingerprint = fingerprint;
    }
}
