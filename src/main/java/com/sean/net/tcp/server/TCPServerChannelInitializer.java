package com.sean.net.tcp.server;

import com.sean.net.codec.handshake.HandshakeCodec;
import com.sean.net.EventServerChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by sean on 22/04/16.
 */
public final class TCPServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final TCPServer server;

    public TCPServerChannelInitializer(TCPServer server) {
        this.server = server;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(HandshakeCodec.class.getName(), new HandshakeCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(EventServerChannelHandler.class.getName(), new EventServerChannelHandler<TCPServer>(server));
    }

}
