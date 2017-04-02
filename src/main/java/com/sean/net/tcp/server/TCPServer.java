package com.sean.net.tcp.server;

import com.sean.net.SocketContext;
import com.sean.net.AbstractServer;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by sean on 25/04/16.
 */
public final class TCPServer extends AbstractServer {

    public TCPServer(SocketContext socketContext) {
        super(socketContext);
    }

    public TCPServer configure() {
        bootstrap.group(group);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new TCPServerChannelInitializer(this));

        return this;
    }
}
