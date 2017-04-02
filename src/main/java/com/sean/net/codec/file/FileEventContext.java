package com.sean.net.codec.file;

import com.sean.net.AbstractServer;
import com.sean.net.ChannelEventContext;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by sean on 05/05/16.
 */
public final class FileEventContext extends ChannelEventContext {

    /**
     * Creates a new {@link ChannelEventContext}.
     *
     * @param channelHandlerContext The {@link ChannelHandlerContext}.
     * @param server                The {@link AbstractServer}.
     */
    public FileEventContext(ChannelHandlerContext channelHandlerContext, AbstractServer server) {
        super(channelHandlerContext, server);
    }

    @Override
    public void onDisconnection() {

    }
}
