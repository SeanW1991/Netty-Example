package com.sean.net;

import com.sean.event.Event;
import com.sean.net.codec.authentication.AuthenticationEventContext;
import com.sean.net.codec.file.FileEventContext;
import com.sean.net.codec.handshake.HandshakeEvent;
import com.sean.net.codec.handshake.HandshakeEventContext;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;

/**
 * Created by sean on 01/05/2016.
 */
public class EventServerChannelHandler<S extends AbstractServer> extends SimpleChannelInboundHandler<Event> {

    /**
     * The {@link AbstractServer}.
     */
    private final AbstractServer server;

    /**
     * Creates a new {@link EventServerChannelHandler}.
     * @param server The {@link AbstractServer}.
     */
    public EventServerChannelHandler(AbstractServer server) {
        this.server = server;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        Attribute<ChannelEventContext> attribute = ctx.attr(server.getEventContextAttributeKey());
        if(attribute.get() == null) {
            attribute.set(new HandshakeEventContext(ctx, server));
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Event event) throws Exception {
        Attribute<ChannelEventContext> attribute = ctx.attr(server.getEventContextAttributeKey());
        if(event instanceof HandshakeEvent) {
            switch(((HandshakeEvent) event).getType()) {
                case AGENT_AUTHENTICATION:
                    attribute.set(new AuthenticationEventContext(ctx, server));
                    break;

                case FILE_SERVING_SERVICE:
                    attribute.set(new FileEventContext(ctx, server));
                    break;
            }
        }
        server.getNetworkEventHub().publish(event, attribute.get());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        ChannelEventContext context = ctx.attr(server.getEventContextAttributeKey()).get();
        if(context != null) {
            context.onDisconnection();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
        throwable.printStackTrace();
    }

}
