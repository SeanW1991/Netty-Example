package com.sean.net;

import com.sean.event.Event;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by sean on 04/05/16.
 */
public final class EventClientChannelHandler<C extends AbstractClient> extends SimpleChannelInboundHandler<Event> {

    private final C client;

    public EventClientChannelHandler(C client) {
        this.client = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Event msg) throws Exception {
        System.out.println("CLIENT "+ msg);
    }
}
