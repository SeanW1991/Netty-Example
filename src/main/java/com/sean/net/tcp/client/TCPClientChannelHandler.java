package com.sean.net.tcp.client;

import com.sean.event.Event;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by sean on 22/04/16.
 */
public final class TCPClientChannelHandler extends SimpleChannelInboundHandler<Event> {

    private final TCPClient client;

    public TCPClientChannelHandler(TCPClient client) {
        this.client = client;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Event msg) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {

    }
}
