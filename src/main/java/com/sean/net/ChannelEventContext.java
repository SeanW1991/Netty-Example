package com.sean.net;

import com.sean.event.EventContext;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by sean on 01/05/2016.
 */
public abstract class ChannelEventContext implements EventContext {

    /**
     * The {@link ChannelHandlerContext}.
     */
    protected final ChannelHandlerContext channelHandlerContext;

    /**
     * The {@link Channel}.
     */
    protected final Channel channel;

    /**
     * The {@link AbstractServer}.
     */
    protected final AbstractServer server;

    /**
     * Creates a new {@link ChannelEventContext}.
     * @param channelHandlerContext The {@link ChannelHandlerContext}.
     * @param server The {@link AbstractServer}.
     */
    protected ChannelEventContext(ChannelHandlerContext channelHandlerContext, AbstractServer server) {
        this.channelHandlerContext = channelHandlerContext;
        this.channel = channelHandlerContext.channel();
        this.server = server;
    }

    /**
     * Used to handle a disconnection of a {@link Channel}.
     */
    public abstract void onDisconnection();

    /**
     * Gets the {@link AbstractServer}.
     * @return The {@code server}.
     */
    public AbstractServer getServer() {
        return server;
    }

    /**
     * Gets the {@link Channel}.
     * @return The {@code channel}.
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Gets the {@link ChannelHandlerContext}.
     * @return The {@code channelHandlerContext}.
     */
    public ChannelHandlerContext getChannelHandlerContext() {
        return channelHandlerContext;
    }
}
