package com.sean.net.codec.authentication;

import com.sean.net.AbstractServer;
import com.sean.net.ChannelEventContext;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by sean on 26/04/16.
 */
public final class AuthenticationEventContext extends ChannelEventContext {

    /**
     * Creates a new {@link ChannelEventContext}.
     *
     * @param channelHandlerContext The {@link ChannelHandlerContext}.
     * @param server The {@link AbstractServer}.
     */
    public AuthenticationEventContext(ChannelHandlerContext channelHandlerContext, AbstractServer server) {
        super(channelHandlerContext, server);
    }

    @Override
    public void onDisconnection() {

    }
}
