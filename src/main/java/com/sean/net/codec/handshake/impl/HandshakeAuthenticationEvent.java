package com.sean.net.codec.handshake.impl;

import com.sean.model.AccountCredentials;
import com.sean.net.codec.handshake.HandshakeType;
import com.sean.utils.ByteBufUtils;
import io.netty.buffer.ByteBufAllocator;

/**
 * Created by sean on 26/04/16.
 */
public class HandshakeAuthenticationEvent extends HandshakeRequestEvent<AccountCredentials> {

    public HandshakeAuthenticationEvent(ByteBufAllocator allo) {
        super(HandshakeType.AGENT_AUTHENTICATION, allo.buffer());
    }

    @Override
    public void addToBuffer(AccountCredentials credentials) {
        ByteBufUtils.addString(credentials.getUsername(), payload);
        ByteBufUtils.addString(credentials.getPassword(), payload);
    }
}
