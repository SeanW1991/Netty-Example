package com.sean.net.codec.handshake;

import com.sean.net.AbstractServer;
import com.sean.net.ChannelEventContext;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by sean on 06/05/16.
 */
public final class HandshakeEventContext extends ChannelEventContext {

    public HandshakeEventContext(ChannelHandlerContext channelHandlerContext, AbstractServer server) {
        super(channelHandlerContext, server);
    }

    @Override
    public void onDisconnection() {

    }
}
