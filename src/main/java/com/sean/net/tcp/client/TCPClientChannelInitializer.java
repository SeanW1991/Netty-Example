package com.sean.net.tcp.client;

import com.sean.net.codec.handshake.HandshakeCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by sean on 22/04/16.
 */
public final class TCPClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final TCPClient client;

    public TCPClientChannelInitializer(TCPClient client) {
        this.client = client;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(HandshakeCodec.class.getName(), new HandshakeCodec());
        pipeline.addLast(TCPClientChannelHandler.class.getName(), new TCPClientChannelHandler(client));
    }

}
