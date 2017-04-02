package com.sean.net.codec.authentication;

import com.sean.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * Created by sean on 26/04/16.
 */
public final class AuthenticationCodec extends ByteToMessageCodec<AuthenticationResponseEvent> {

    @Override
    protected void encode(ChannelHandlerContext ctx, AuthenticationResponseEvent msg, ByteBuf out) throws Exception {

        AuthenticationResponseType type = msg.getType();
        out.writeByte(type.ordinal());
        if(msg.getPayload() != null) {
            out.writeBytes(msg.getPayload());
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (!in.isReadable()) {
            throw new IllegalArgumentException("Error authentication decoder contains no readable bytes");
        }

        int size = in.readUnsignedShort();
        if(size >= in.readableBytes()) {
            String username = ByteBufUtils.readString(in);
            String password = ByteBufUtils.readString(in);
            out.add(new AuthenticationRequestEvent(username, password));
        }
    }
}
