package com.sean.net.codec.file;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.stream.ChunkedFile;

/**
 * Created by sean on 06/05/16.
 */
public final class FileChannelHandler extends SimpleChannelInboundHandler<ChunkedFile> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChunkedFile msg) throws Exception {
        System.out.println(msg);
    }
}
