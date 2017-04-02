package com.sean.net.tcp.client;

import com.sean.model.AccountCredentials;
import com.sean.net.AbstractClient;
import com.sean.net.SocketContext;
import com.sean.net.codec.handshake.impl.HandshakeAuthenticationEvent;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.SocketAddress;
import java.util.logging.Logger;

/**
 * Created by sean on 25/04/16.
 */
public final class TCPClient extends AbstractClient {

    private final Logger logger = Logger.getLogger(TCPClient.class.getName());

    public TCPClient(SocketContext socketContext) {
        super(socketContext);
    }

    @Override
    public TCPClient configure() {
        logger.info("Configuring client network.....");
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new TCPClientChannelInitializer(this));
        logger.info("Client network configured.....");
        return this;
    }

    @Override
    public void connect() {
        SocketAddress address = socketContext.getAddress();
        try {
            ChannelFuture future = bootstrap.connect(socketContext.getAddress()).sync();
            if (future.isSuccess()) {
                logger.info("Successfully connected to " + address);
                Channel channel = future.channel();
                HandshakeAuthenticationEvent handshakeEvent = new HandshakeAuthenticationEvent(channel.alloc());
                handshakeEvent.addToBuffer(new AccountCredentials("username", "password"));
                channel.writeAndFlush(handshakeEvent);
            }
        } catch (InterruptedException cause) {
            throw new RuntimeException("Failed to bind to " + address, cause);
        }
    }
}
