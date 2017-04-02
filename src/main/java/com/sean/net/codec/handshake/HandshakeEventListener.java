package com.sean.net.codec.handshake;

import com.sean.event.impl.DefaultEventListener;
import com.sean.net.ChannelEventContext;
import io.netty.channel.ChannelPipeline;

/**
 * Created by sean on 06/05/16.
 */
public final class HandshakeEventListener implements DefaultEventListener<HandshakeEvent,ChannelEventContext> {

    @Override
    public EventStage onEvent(HandshakeEvent event, ChannelEventContext context) {
        ChannelPipeline pipeline = context.getChannel().pipeline();
        return EventStage.EVENT_STOP;
    }
}
