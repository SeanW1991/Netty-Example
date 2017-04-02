package com.sean.net.codec.handshake;

import com.sean.net.EventServerChannelHandler;
import com.sean.net.codec.authentication.AuthenticationCodec;
import com.sean.net.codec.file.FileCodec;
import com.sean.net.codec.handshake.impl.HandshakeRequestEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * Created by sean on 25/04/16.
 */
public final class HandshakeCodec extends ByteToMessageCodec<HandshakeRequestEvent> {

    @Override
    protected void encode(ChannelHandlerContext ctx, HandshakeRequestEvent msg, ByteBuf out) throws Exception {
        int service = msg.getType().ordinal();
        ByteBuf msgPayload = msg.getPayload();

        out.writeByte(service);
        out.writeShort(msgPayload.writerIndex());
        out.writeBytes(msg.getPayload());

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (!in.isReadable()) {
            throw new IllegalArgumentException("Error handshake contains no readable bytes");
        }
        int service = in.readUnsignedByte();
        HandshakeType type = HandshakeType.values()[service];
        ChannelPipeline pipeline = ctx.pipeline();
        out.add(new HandshakeEvent(type));
        switch (type) {
            case AGENT_AUTHENTICATION:
                pipeline.addBefore(EventServerChannelHandler.class.getName(), AuthenticationCodec.class.getName(), new AuthenticationCodec());
                break;
            case FILE_SERVING_SERVICE:
                pipeline.addBefore(EventServerChannelHandler.class.getName(), FileCodec.class.getName(), new FileCodec());
                break;
        }
        if (in.isReadable()) {
            out.add(in.readBytes(in.readableBytes()));
        }
        pipeline.remove(HandshakeCodec.class.getName());
    }
}
