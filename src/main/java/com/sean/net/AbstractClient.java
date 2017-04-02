package com.sean.net;

import com.sean.net.tcp.client.TCPClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by sean on 28/04/16.
 */
public abstract class AbstractClient {

    /**
     * The dedicated {@link Logger} for this class.
     */
    protected final Logger logger = Logger.getLogger(TCPClient.class.getName());

    /**
     * The {@link Bootstrap} for the interface between netty and its client layer.
     */
    protected final Bootstrap bootstrap = new Bootstrap();

    /**
     * The {@link EventLoopGroup}.
     */
    protected EventLoopGroup group = new NioEventLoopGroup();

    /**
     * The default reconnecting delay
     */
    public static final int RECONNECTION_DELAY = 5;

    /**
     * The {@link AbstractClient} used to contain information about both the client and server.
     */
    protected final SocketContext socketContext;

    /**
     * Creates a new {@link AbstractClient}.
     * @param socketContext The socketContext.
     */
    protected AbstractClient(SocketContext socketContext) {
        this.socketContext = socketContext;
    }

    /**
     * Used to configure the client network settings that need to be
     * set before a connection can be created.
     * @return The {@link AbstractClient} for the use of chaining.
     */
    public abstract AbstractClient configure();

    /**
     * Creates a connection to the server designed in the {@code socketContext}.
     */
    public abstract void connect() throws InterruptedException;

    /**
     * Used to reconnect to a server that is defined in the {@code socketContext}.
     * @param ctx The {@link ChannelHandlerContext} to create the new connection.
     */
    public void reconnect(ChannelHandlerContext ctx) {
        EventLoop loop = ctx.channel().eventLoop();
        loop.schedule((Runnable) () -> {
            try {
                configure().connect();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, RECONNECTION_DELAY, TimeUnit.SECONDS);
    }

    /**
     * Gets the {@link SocketContext}.
     * @return The {@code socketContext}.
     */
    public SocketContext getSocketContext() {
        return socketContext;
    }
}
