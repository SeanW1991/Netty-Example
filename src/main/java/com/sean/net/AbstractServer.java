package com.sean.net;

import com.sean.event.Event;
import com.sean.event.EventContext;
import com.sean.event.impl.DefaultEventHub;
import com.sean.net.codec.authentication.AuthenticationEventListener;
import com.sean.net.codec.authentication.AuthenticationRequestEvent;
import com.sean.net.codec.handshake.HandshakeEvent;
import com.sean.net.codec.handshake.HandshakeEventListener;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;
import java.util.logging.Logger;

/**
 * Created by sean on 28/04/16.
 */
public abstract class AbstractServer {

    /**
     * The dedicated logger for the class.
     */
    private final Logger logger = Logger.getLogger(AbstractServer.class.getName());

    /**
     * The {@link DefaultEventHub} containing events for the handling any network type of events.
     */
    protected final DefaultEventHub<Event, ChannelEventContext> networkEventHub = new DefaultEventHub<>();

    /**
     * The {@link EventLoopGroup} used to allow for the registering of {@link io.netty.channel.Channel}s so
     * they can get processed for later selection during the event loop within netty's core.
     */
     protected EventLoopGroup group = new NioEventLoopGroup();

    /**
     * The {@link ServerBootstrap} for the bootstraping of the server.
     */
    protected final ServerBootstrap bootstrap = new ServerBootstrap();

    /**
     * The {@link AttributeKey} used to access a {@link io.netty.util.Attribute} type of {@link EventContext} within the {@link io.netty.util.AttributeMap}.
     */
    protected final AttributeKey<ChannelEventContext> eventContextAttributeKey = AttributeKey.valueOf("event_session_context");

    /**
     * The {@link SocketContext} that contains information for the server to be bound.
     */
    protected final SocketContext socketContext;

    /**
     * Creates a new {@link AbstractServer}.
     * @param socketContext The {@link SocketContext} to create.
     */
    protected AbstractServer(SocketContext socketContext) {
        this.socketContext = socketContext;

        networkEventHub.subscribe(AuthenticationRequestEvent.class, new AuthenticationEventListener());
        networkEventHub.subscribe(HandshakeEvent.class, new HandshakeEventListener());
    }

    /**
     * Configures the {@link AbstractServer} so it is ready for a port to be bound to it.
     * @return
     */
    public abstract AbstractServer configure();

    /**
     * Binds the server to a specific port based on the {@code socketContext} .
     */
    public void bind() {
        InetSocketAddress address = socketContext.getAddress();
        try {
            ChannelFuture future = bootstrap.bind(socketContext.getAddress()).sync();
            if(future.isSuccess()) {
                logger.info("Agent now bound to address: " + address);
            }
        } catch (InterruptedException cause) {
            throw new RuntimeException("Failed to bind to " + address, cause);
        }
    }

    /**
     * Gets the {@link io.netty.util.AttributeKey}.
     * @return The {@code eventContextAttributeKey}.
     */
    public AttributeKey<ChannelEventContext> getEventContextAttributeKey() {
        return eventContextAttributeKey;
    }

    /**
     * Gets the {@link DefaultEventHub} for the network {@link Event}s.
     * @return The {@code networkEventHub}.
     */
    public DefaultEventHub<Event, ChannelEventContext> getNetworkEventHub() {
        return networkEventHub;
    }

    /**
     * Gets the {@link ServerBootstrap}.
     * @return The {@code bootstrap}.
     */
    public ServerBootstrap getBootstrap() {
        return bootstrap;
    }

    /**
     * Gets the {@link EventLoopGroup}.
     * @return The {@code group}.
     */
    public EventLoopGroup getGroup() {
        return group;
    }
}
