package com.sean.net.codec.file;

import com.sean.utils.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.io.File;
import java.util.List;

/**
 * Created by sean on 05/05/16.
 */
public final class FileCodec extends ByteToMessageCodec<FileResponseEvent> {

    @Override
    protected void encode(ChannelHandlerContext ctx, FileResponseEvent msg, ByteBuf out) throws Exception {
        out.writeByte(msg.getResponseType().ordinal());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int size = in.readUnsignedShort();
        if(size >= in.readableBytes()) {
            String fileDirectory = ByteBufUtils.readString(in);
            File file = new File(fileDirectory);
            out.add(new FileRequestEvent(file));
        }
    }
}
